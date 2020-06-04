package org.example.investmentfunds.investment;

import lombok.Builder;
import lombok.Data;
import org.example.investmentfunds.result.CalculationResult;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
@Builder
public class InvestmentCalculation {

    private List<CalculationResult> resultList;
    private BigInteger undistributedRest;
}
