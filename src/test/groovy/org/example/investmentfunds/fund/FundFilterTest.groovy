package org.example.investmentfunds.fund

import org.example.investmentfunds.TestBase

class FundFilterTest extends TestBase {

    def "should return IDs list from provided Funds list"() {
        given:
        def expectedResult = [1L, 2L, 3L, 4L, 5L, 6L]
        when:
        def obtainedResult = FundFilter.getListOfIDs(fundsList)
        then:
        obtainedResult == expectedResult
    }

    def "should filter polish funds from list"() {
        given:
        def expectedResult = [
                Fund.builder().id(1).type(FundType.POLISH).name("Fundusz Polski 1").build(),
                Fund.builder().id(2).type(FundType.POLISH).name("Fundusz Polski 2").build(),
        ]
        def fundType = FundType.POLISH
        when:
        def obtainedResult = FundFilter.filterFundsByType(fundsList, fundType)
        then:
        obtainedResult == expectedResult
    }
}
