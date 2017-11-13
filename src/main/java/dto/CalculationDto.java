package dto;

import lombok.Data;

import java.util.Date;

@Data
public class CalculationDto {


    private Integer value;
    private String fromCurrency;
    private String toCurrency;
    private Date date;
}
