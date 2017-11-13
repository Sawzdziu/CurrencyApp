package rest.mappings;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyDateJson {

    private String table;
    private String currency;
    private String code;
    private List<CurrencyItemJson> rates;
}
