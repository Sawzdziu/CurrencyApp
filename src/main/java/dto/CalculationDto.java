package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculationDto {

    private Integer value;
    private String fromCurrency;
    private String toCurrency;
    private Date date;
}
