package org.example.investmentfunds.distribution

import org.example.investmentfunds.TestBase
import org.example.investmentfunds.result.CalculationResult

class UndistributedRestCalculatorTest extends TestBase {
    def "should calculate undistributed rest"() {
        given:
        def amountToDistribute = new BigInteger("10001")
        def providedListOfResults = [
                CalculationResult.builder().fundID(1L).amount("5000").percent("50").build(),
                CalculationResult.builder().fundID(2L).amount("3500").percent("35").build(),
                CalculationResult.builder().fundID(3L).amount("1500").percent("15").build(),
        ]
        def expectedCalculatedRest = 1
        when:
        def calculatedRest = undistributedRestCalculator.calculateUndistributedRest(amountToDistribute, providedListOfResults)
        then:
        calculatedRest == expectedCalculatedRest
    }
}
