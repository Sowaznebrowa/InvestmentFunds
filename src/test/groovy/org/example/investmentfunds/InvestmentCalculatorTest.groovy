package org.example.investmentfunds

import org.example.investmentfunds.investment.style.Safe
import org.example.investmentfunds.model.AmountPercentPair
import org.example.investmentfunds.model.Fund
import org.example.investmentfunds.model.FundType
import spock.lang.Specification

class InvestmentCalculatorTest extends Specification {

    def fundList = [
            Fund.builder().id(1).type(FundType.POLISH).name("Fundusz Polski 1").build(),
            Fund.builder().id(2).type(FundType.POLISH).name("Fundusz Polski 2").build(),
            Fund.builder().id(3).type(FundType.FOREIGN).name("Fundusz Zagraniczny 1").build(),
            Fund.builder().id(4).type(FundType.FOREIGN).name("Fundusz Zagraniczny 2").build(),
            Fund.builder().id(5).type(FundType.FOREIGN).name("Fundusz Zagraniczny 3").build(),
            Fund.builder().id(6).type(FundType.CASH).name("Fundusz Pieniężny 1").build(),
    ]

    InvestmentCalculator ic = new InvestmentCalculatorImpl()

    def "should return funds distribution"() {
        given:
        def expectedOutputMap = [
                1L:AmountPercentPair.builder().fundID(1L).amount(1000).percent("10").build(),
                2L:AmountPercentPair.builder().fundID(2L).amount(1000).percent("10").build(),
                3L:AmountPercentPair.builder().fundID(3L).amount(2500).percent("25").build(),
                4L:AmountPercentPair.builder().fundID(4L).amount(2500).percent("25").build(),
                5L:AmountPercentPair.builder().fundID(5L).amount(2500).percent("25").build(),
                6L:AmountPercentPair.builder().fundID(6L).amount(500).percent("5").build(),
        ]
        def expectedOutputRest = 0
        when:
        def calculatedDistribution= ic.calculateFundsDistribution(fundList, 10000, new Safe())
        then:
        calculatedDistribution.distributionMap == expectedOutputMap
        calculatedDistribution.undistributedRest == expectedOutputRest
    }

    def "should return funds distribution with undistributed rest"() {
        given:
        def expectedOutputMap = [
                1L:AmountPercentPair.builder().fundID(1L).amount(1000).percent("10").build(),
                2L:AmountPercentPair.builder().fundID(2L).amount(1000).percent("10").build(),
                3L:AmountPercentPair.builder().fundID(3L).amount(2500).percent("25").build(),
                4L:AmountPercentPair.builder().fundID(4L).amount(2500).percent("25").build(),
                5L:AmountPercentPair.builder().fundID(5L).amount(2500).percent("25").build(),
                6L:AmountPercentPair.builder().fundID(6L).amount(500).percent("5").build(),
        ]
        def expectedOutputRest = 1
        when:
        def calculatedDistribution = ic.calculateFundsDistribution(fundList, 10001, new Safe())
        then:
        calculatedDistribution.distributionMap == expectedOutputMap
        calculatedDistribution.undistributedRest == expectedOutputRest
    }

    def "should return funds distribution with undistributed rest with 2 digits precision"() {
        given:
        def fundList = [
                Fund.builder().id(1).type(FundType.POLISH).name("Fundusz Polski 1").build(),
                Fund.builder().id(2).type(FundType.POLISH).name("Fundusz Polski 2").build(),
                Fund.builder().id(3).type(FundType.POLISH).name("Fundusz Polski 3").build(),
                Fund.builder().id(4).type(FundType.FOREIGN).name("Fundusz Zagraniczny 1").build(),
                Fund.builder().id(5).type(FundType.FOREIGN).name("Fundusz Zagraniczny 2").build(),
                Fund.builder().id(6).type(FundType.CASH).name("Fundusz Pieniężny 1").build(),
        ]
        def expectedOutputMap = [
                1L:AmountPercentPair.builder().fundID(1L).amount(668).percent("6.68").build(),
                2L:AmountPercentPair.builder().fundID(2L).amount(666).percent("6.66").build(),
                3L:AmountPercentPair.builder().fundID(3L).amount(666).percent("6.66").build(),
                4L:AmountPercentPair.builder().fundID(4L).amount(3750).percent("37.5").build(),
                5L:AmountPercentPair.builder().fundID(5L).amount(3750).percent("37.5").build(),
                6L:AmountPercentPair.builder().fundID(6L).amount(500).percent("5").build(),
        ]
        def expectedOutputRest = 0
        when:
        def calculatedDistribution = ic.calculateFundsDistribution(fundList, 10000, new Safe())
        then:
        calculatedDistribution.distributionMap == expectedOutputMap
        calculatedDistribution.undistributedRest == expectedOutputRest
    }

}
