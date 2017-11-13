package rest.mappings;

import lombok.Data;

@Data
public class CurrencyItemJson {

    private String currency;
    private String code;
    private double mid;
}
