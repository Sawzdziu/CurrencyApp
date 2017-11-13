package calculation;

import dto.StatisticDto;
import dto.StatisticInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.mappings.CurrencyItemJson;
import rest.service.clients.DateParameterClient;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    @Autowired
    private DateParameterClient dateParameterClient;

    public StatisticDto resolve(StatisticInputDto statisticInputDto) {
        List<CurrencyItemJson> currencyItemsJson = getCurrencyDateJson(statisticInputDto.getCode(), statisticInputDto.getDays());
        DoubleSummaryStatistics stats = currencyItemsJson.stream().mapToDouble(CurrencyItemJson::getMid).summaryStatistics();
        return new StatisticDto(stats.getMax(), stats.getMin(), stats.getAverage(), standardDeviation(stats.getAverage(), currencyItemsJson.stream().map(CurrencyItemJson::getMid).collect(Collectors.toList())));
    }

    private List<CurrencyItemJson> getCurrencyDateJson(String code, Integer days) {
        return dateParameterClient.sendRequest(code, days.toString()).getRates();
    }

    private Double standardDeviation(Double average, List<Double> values) {
        if (values.size() == 1) {
            return 0.0;
        } else {
            Double temp = 0.0;
            for (Double rate : values) {
                temp += (rate - average) * (rate - average);
            }
            temp = temp / (values.size() - 1);
            return Math.sqrt(temp);
        }
    }
}
