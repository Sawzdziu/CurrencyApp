package calculation;

import dto.CalculationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import rest.mappings.CurrencyDateJSON;
import rest.service.clients.AverageRateFromDateClient;
import synchronization.resolver.CurrencyEnum;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
public class CalculateService {

    @Autowired
    private AverageRateFromDateClient averageRateFromDateClient;

    public Double calculate(CalculationDto calculationDto) {
        if (calculationDto.getFromCurrency().toUpperCase().equals(CurrencyEnum.PLN.toString())) {
            CurrencyDateJSON currencyDateJSON = getCurrencyToRates(calculationDto);
            return calculationDto.getValue() / getMidValue(currencyDateJSON);
        } else if (calculationDto.getToCurrency().toUpperCase().equals(CurrencyEnum.PLN.toString())) {
            CurrencyDateJSON currencyDateJSON = getCurrencyFromRates(calculationDto);
            return calculationDto.getValue() * getMidValue(currencyDateJSON);
        } else {
            CurrencyDateJSON currencyDateFrom = getCurrencyFromRates(calculationDto);
            CurrencyDateJSON currencyDateTo = getCurrencyToRates(calculationDto);
            return calculationDto.getValue() * (getMidValue(currencyDateFrom) / getMidValue(currencyDateTo));
        }
    }

    private String resolveDate(Date date) {
        return LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date)).toString();
    }

    private CurrencyDateJSON getCurrencyToRates(CalculationDto calculationDto) {
        return averageRateFromDateClient.sendRequest(calculationDto.getToCurrency(), resolveDate(calculationDto.getDate()));
    }

    private CurrencyDateJSON getCurrencyFromRates(CalculationDto calculationDto) {
        return averageRateFromDateClient.sendRequest(calculationDto.getFromCurrency(), resolveDate(calculationDto.getDate()));
    }

    private Double getMidValue(CurrencyDateJSON currencyDateJSON) {
        return currencyDateJSON.getRates().get(0).getMid();
    }
}
