package org.example.investmentfunds.distribution;

import org.example.investmentfunds.result.CalculationResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DistributionCalculator {

    private static final int FIRST_ELEMENT = 0;

    public List<BigDecimal> calculatePercentageDistribution(Integer numberOfFunds, BigDecimal percentageToDistribute) {

        BigDecimal singleFundRatio = percentageToDistribute.divide(BigDecimal.valueOf(numberOfFunds), 4, RoundingMode.DOWN);
        List<BigDecimal> listOfDistributedPercentage = new ArrayList<>(Collections.nCopies(numberOfFunds, singleFundRatio));
        if (distributionHasUnassignedRest(percentageToDistribute, listOfDistributedPercentage)) {
            BigDecimal delta = percentageToDistribute.subtract(singleFundRatio.multiply(BigDecimal.valueOf(numberOfFunds)));
            BigDecimal enlargedSingleFundRatio = singleFundRatio.add(delta);

            listOfDistributedPercentage.set(FIRST_ELEMENT, enlargedSingleFundRatio);
        }
        return listOfDistributedPercentage;
    }

    public List<Integer> calculateAmountDistribution(List<BigDecimal> listOfPercentageDistribution, Integer amount) {
        return listOfPercentageDistribution.stream()
                                           .map(bigDecimal -> bigDecimal.multiply(BigDecimal.valueOf(amount))
                                                                        .intValue())
                                           .collect(Collectors.toList());
    }

    public Integer calculateUndistributedRest(Integer investmentAmount, List<CalculationResult> listOfResults) {
        Integer distributedSum = listOfResults
                .stream()
                .map(CalculationResult::getAmount)
                .reduce(Integer::sum)
                .orElse(0);
        return investmentAmount - distributedSum;
    }

    private boolean distributionHasUnassignedRest(BigDecimal percentageToDistribute, List<BigDecimal> listOfDistributedPercentage) {
        return !listOfDistributedPercentage.stream()
                                           .reduce(BigDecimal::add)
                                           .orElse(BigDecimal.ZERO)
                                           .equals(percentageToDistribute);
    }
}
