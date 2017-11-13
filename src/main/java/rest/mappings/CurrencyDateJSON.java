package rest.mappings;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyDateJSON {

    private String table;
    private String currency;
    private String code;
    private List<CurrencyJSON> rates;
}
