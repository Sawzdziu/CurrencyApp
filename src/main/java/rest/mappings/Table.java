package rest.mappings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Table {

    private String table;
    private String no;
    private Date effectiveDate;
    private List<CurrencyItemJson> rates;
}
