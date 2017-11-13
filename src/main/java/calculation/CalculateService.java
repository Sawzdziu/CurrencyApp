package calculation;

import dto.CalculationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.mappings.CurrencyDateJson;
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
            CurrencyDateJson currencyDateJson = getCurrencyToRates(calculationDto);
            return calculationDto.getValue() / getMidValue(currencyDateJson);
        } else if (calculationDto.getToCurrency().toUpperCase().equals(CurrencyEnum.PLN.toString())) {
            CurrencyDateJson currencyDateJson = getCurrencyFromRates(calculationDto);
            return calculationDto.getValue() * getMidValue(currencyDateJson);
        } else {
            CurrencyDateJson currencyDateFrom = getCurrencyFromRates(calculationDto);
            CurrencyDateJson currencyDateTo = getCurrencyToRates(calculationDto);
            return calculationDto.getValue() * (getMidValue(currencyDateFrom) / getMidValue(currencyDateTo));
        }
    }

    private String resolveDate(Date date) {
        return LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date)).toString();
    }

    private CurrencyDateJson getCurrencyToRates(CalculationDto calculationDto) {
        return averageRateFromDateClient.sendRequest(calculationDto.getToCurrency(), resolveDate(calculationDto.getDate()));
    }

    private CurrencyDateJson getCurrencyFromRates(CalculationDto calculationDto) {
        return averageRateFromDateClient.sendRequest(calculationDto.getFromCurrency(), resolveDate(calculationDto.getDate()));
    }

    private Double getMidValue(CurrencyDateJson currencyDateJson) {
        return currencyDateJson.getRates().get(0).getMid();
    }
}
