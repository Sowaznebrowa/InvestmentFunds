package org.example.investmentfunds.distribution;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class AmountDistributionCalculator {

    public List<BigDecimal> calculateAmountDistribution(List<BigDecimal> listOfPercentageDistribution, BigInteger amount) {
        return listOfPercentageDistribution.stream()
                .map(bigDecimal -> bigDecimal.multiply(new BigDecimal(amount)))
                .collect(Collectors.toList());
    }
}
