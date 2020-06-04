package org.example.investmentfunds.distribution

import org.example.investmentfunds.TestBase

class AmountDistributionCalculatorTest extends TestBase {

    def "should calculate amount distribution"() {
        given:
        def amountToDistribute = new BigInteger("10000")
        def providedPercentageDistribution = [
                new BigDecimal("0.0834"),
                new BigDecimal("0.0833"),
                new BigDecimal("0.0833"),
        ]
        def expectedAmountDistribution = [
                new BigDecimal(834),
                new BigDecimal(833),
                new BigDecimal(833)
        ]
        when:
        def calculatedDistribution = amountDistributionCalculator.calculateAmountDistribution(providedPercentageDistribution, amountToDistribute)
        then:
        calculatedDistribution == expectedAmountDistribution
    }

}
