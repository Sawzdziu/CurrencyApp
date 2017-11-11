package rest.mappings;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Rates implements Serializable{

    private String no;
    private Date date;
    private Double mid;
}
