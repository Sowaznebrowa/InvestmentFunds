package org.example.investmentfunds.result

import org.example.investmentfunds.TestBase
import spock.lang.Unroll

class ResultListProducerTest extends TestBase {

    static def listOfIDs = [1L, 2L, 3L]
    static def percentageDistribution = [
            new BigDecimal("0.0834"),
            new BigDecimal("0.0833"),
            new BigDecimal("0.0833"),
    ]
    static def amountDistribution = [
            new Integer(834),
            new Integer(833),
            new Integer(833),
    ]

    def "Should produce result list from provided distribution"() {
        given:
        def expectedResult = [
                CalculationResult.builder().fundID(1L).percent("8.34%").amount(834).build(),
                CalculationResult.builder().fundID(2L).percent("8.33%").amount(833).build(),
                CalculationResult.builder().fundID(3L).percent("8.33%").amount(833).build(),
        ]

        when:
        def obtainedResult = resultListProducer.createCalculationResultList(
                listOfIDs,
                percentageDistribution,
                amountDistribution)

        then:
        obtainedResult == expectedResult
    }

    @Unroll
    def "Should throw exception when one of arguments has different size"() {

        when:
        resultListProducer.createCalculationResultList(
                idsList as List<Long>,
                percentageList as List<BigDecimal>,
                amountList as List<Integer>)

        then:
        def exception = thrown(expectedException)
        exception.message == exceptionMessage

        where:
        idsList   || percentageList             || amountList         || expectedException              || exceptionMessage
        [1L, 2L]  || percentageDistribution     || amountDistribution || NonEqualArgumentsSizeException || "All method arguments should be equal size"
        listOfIDs || [new BigDecimal("0.0834")] || amountDistribution || NonEqualArgumentsSizeException || "All method arguments should be equal size"
        listOfIDs || percentageDistribution     || [new Integer(834)] || NonEqualArgumentsSizeException || "All method arguments should be equal size"
    }
}
