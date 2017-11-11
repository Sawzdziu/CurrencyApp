package rest.mappings;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PeriodCurrency implements Serializable{

    private String tabe;
    private String currency;
    private String code;
    private List<Rates> rates;
}
