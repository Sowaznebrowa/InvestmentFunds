package org.example.investmentfunds.distribution

import org.example.investmentfunds.TestBase
import org.example.investmentfunds.result.CalculationResult

class DistributionCalculatorTest extends TestBase {

    DistributionCalculator distributionCalculator = new DistributionCalculator()

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
        def calculatedDistribution = distributionCalculator.calculatePercentageDistribution(numberOfFunds, percentageToDistribute)
        then:
        calculatedDistribution == expectedDistribution
    }

    def "should calculate amount distribution"() {
        given:
        def amountToDistribute = 10000
        def providedPercentageDistribution = [
                new BigDecimal("0.0834"),
                new BigDecimal("0.0833"),
                new BigDecimal("0.0833"),
        ]
        def expectedAmountDistribution = [
                new Integer(834),
                new Integer(833),
                new Integer(833)
        ]
        when:
        def calculatedDistribution = distributionCalculator.calculateAmountDistribution(providedPercentageDistribution, amountToDistribute)
        then:
        calculatedDistribution == expectedAmountDistribution
    }

    def "should calculate undistributed rest"() {
        given:
        def amountToDistribute = 10001
        def providedListOfResults = [
                CalculationResult.builder().fundID(1L).amount(5000).percent("50").build(),
                CalculationResult.builder().fundID(2L).amount(3500).percent("35").build(),
                CalculationResult.builder().fundID(3L).amount(1500).percent("15").build(),
        ]
        def expectedCalculatedRest = 1
        when:
        def calculatedRest = distributionCalculator.calculateUndistributedRest(amountToDistribute, providedListOfResults)
        then:
        calculatedRest == expectedCalculatedRest
    }
}
