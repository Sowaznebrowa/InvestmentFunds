package org.example.investmentfunds

import org.example.investmentfunds.investment.style.Safe
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
            1L:1000,
            2L:1000,
            3L:2500,
            4L:2500,
            5L:2500,
            6L:500,
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
                1L:1000,
                2L:1000,
                3L:2500,
                4L:2500,
                5L:2500,
                6L:500,
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
                1L:668,
                2L:666,
                3L:666,
                4L:3750,
                5L:3750,
                6L:500,
        ]
        def expectedOutputRest = 0
        when:
        def calculatedDistribution = ic.calculateFundsDistribution(fundList, 10000, new Safe())
        then:
        calculatedDistribution.distributionMap == expectedOutputMap
        calculatedDistribution.undistributedRest == expectedOutputRest
    }

}
