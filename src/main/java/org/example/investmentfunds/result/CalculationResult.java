package org.example.investmentfunds.result;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
public class CalculationResult {

    private Long fundID;
    private String amount;
    private String percent;
}
