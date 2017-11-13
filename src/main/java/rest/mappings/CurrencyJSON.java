package rest.mappings;

import lombok.Data;

@Data
public class CurrencyJSON {

    private String currency;
    private String code;
    private double mid;
}
