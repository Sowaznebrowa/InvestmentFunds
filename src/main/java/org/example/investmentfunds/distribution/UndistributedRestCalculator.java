package org.example.investmentfunds.distribution;

import org.example.investmentfunds.result.CalculationResult;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class UndistributedRestCalculator {

    public BigInteger calculateUndistributedRest(BigInteger investmentAmount, List<CalculationResult> listOfResults) {
        BigDecimal distributedSum = listOfResults
                .stream()
                .map(CalculationResult::getAmount)
                .map(BigDecimal::new)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        return investmentAmount.subtract(distributedSum.toBigInteger());
    }
}
