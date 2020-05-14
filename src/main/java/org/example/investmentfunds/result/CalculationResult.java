package org.example.investmentfunds.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculationResult {

    private Long fundID;
    private Integer amount;
    private String percent;
}
