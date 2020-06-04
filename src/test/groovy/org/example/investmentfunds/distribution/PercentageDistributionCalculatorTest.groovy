package org.example.investmentfunds.distribution

import org.example.investmentfunds.TestBase
import org.example.investmentfunds.result.CalculationResult

class PercentageDistributionCalculatorTest extends TestBase {

    def "should calculate percentage distribution"() {
        given:
        def numberOfFunds = 3
        def percentageToDistribute = new BigDecimal("0.25")
        def expectedDistribution = [
                new BigDecimal("0.0834"),
                new BigDecimal("0.0833"),
                new BigDecimal("0.0833"),
        ]
        when:
        def calculatedDistribution = percentageDistributionCalculator.calculatePercentageDistribution(numberOfFunds, percentageToDistribute)
        then:
        calculatedDistribution == expectedDistribution
    }

}
