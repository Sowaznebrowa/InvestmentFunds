package org.example.investmentfunds.distribution;

import org.example.investmentfunds.result.CalculationResult;

import java.math.BigDecimal;
import java.util.List;

public interface DistributionCalculator {

    List<BigDecimal> calculatePercentageDistribution(Integer numberOfFunds, BigDecimal percentageToDistribute);

    List<Integer> calculateAmountDistribution(List<BigDecimal> listOfPercentageDistribution, Integer amount);

    Integer calculateUndistributedRest(Integer investmentAmount, List<CalculationResult> listOfResults);

}
