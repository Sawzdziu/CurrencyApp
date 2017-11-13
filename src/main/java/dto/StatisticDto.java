package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticDto {

    private Double max;
    private Double min;
    private Double avg;
    private Double sd;


}
