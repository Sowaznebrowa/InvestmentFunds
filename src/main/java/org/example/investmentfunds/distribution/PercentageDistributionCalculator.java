package org.example.investmentfunds.distribution;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PercentageDistributionCalculator {

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

    private boolean distributionHasUnassignedRest(BigDecimal percentageToDistribute, List<BigDecimal> listOfDistributedPercentage) {
        return !listOfDistributedPercentage.stream()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .equals(percentageToDistribute);
    }
}
