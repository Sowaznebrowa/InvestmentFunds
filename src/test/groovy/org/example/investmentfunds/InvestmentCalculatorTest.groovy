package org.example.investmentfunds

import org.example.investmentfunds.distribution.DistributionCalculator
import org.example.investmentfunds.distribution.DistributionCalculatorImpl
import org.example.investmentfunds.fund.Fund
import org.example.investmentfunds.fund.FundType
import org.example.investmentfunds.investment.style.Agressive
import org.example.investmentfunds.investment.style.Balanced
import org.example.investmentfunds.investment.style.Safe
import org.example.investmentfunds.result.CalculationResult
import org.example.investmentfunds.result.ResultListProducer
import org.example.investmentfunds.result.ResultListProducerImpl
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

    DistributionCalculator dc = new DistributionCalculatorImpl()
    ResultListProducer rlp = new ResultListProducerImpl()
    InvestmentCalculator ic = new InvestmentCalculatorImpl(dc, rlp)

    def "should return funds distribution for safe style"() {
        given:
        def expectedOutputList = [
                CalculationResult.builder().fundID(1L).amount(1000).percent("10").build(),
                CalculationResult.builder().fundID(2L).amount(1000).percent("10").build(),
                CalculationResult.builder().fundID(3L).amount(2500).percent("25").build(),
                CalculationResult.builder().fundID(4L).amount(2500).percent("25").build(),
                CalculationResult.builder().fundID(5L).amount(2500).percent("25").build(),
                CalculationResult.builder().fundID(6L).amount(500).percent("5").build(),
        ]
        def expectedOutputRest = 0
        when:
        def calculatedDistribution = ic.calculateFundsDistribution(fundList, 10000, new Safe())
        then:
        calculatedDistribution.resultList == expectedOutputList
        calculatedDistribution.undistributedRest == expectedOutputRest
    }

    def "should return funds distribution for agressive style"() {
        given:
        def expectedOutputList = [
                CalculationResult.builder().fundID(1L).amount(2000).percent("20").build(),
                CalculationResult.builder().fundID(2L).amount(2000).percent("20").build(),
                CalculationResult.builder().fundID(3L).amount(668).percent("6.68").build(),
                CalculationResult.builder().fundID(4L).amount(666).percent("6.66").build(),
                CalculationResult.builder().fundID(5L).amount(666).percent("6.66").build(),
                CalculationResult.builder().fundID(6L).amount(4000).percent("40").build(),
        ]
        def expectedOutputRest = 0
        when:
        def calculatedDistribution = ic.calculateFundsDistribution(fundList, 10000, new Agressive())
        then:
        calculatedDistribution.resultList == expectedOutputList
        calculatedDistribution.undistributedRest == expectedOutputRest
    }

    def "should return funds distribution for balanced style"() {
        given:
        def expectedOutputList = [
                CalculationResult.builder().fundID(1L).amount(1500).percent("15").build(),
                CalculationResult.builder().fundID(2L).amount(1500).percent("15").build(),
                CalculationResult.builder().fundID(3L).amount(2000).percent("20").build(),
                CalculationResult.builder().fundID(4L).amount(2000).percent("20").build(),
                CalculationResult.builder().fundID(5L).amount(2000).percent("20").build(),
                CalculationResult.builder().fundID(6L).amount(1000).percent("10").build(),
        ]
        def expectedOutputRest = 0
        when:
        def calculatedDistribution = ic.calculateFundsDistribution(fundList, 10000, new Balanced())
        then:
        calculatedDistribution.resultList == expectedOutputList
        calculatedDistribution.undistributedRest == expectedOutputRest
    }

    def "should return funds distribution with undistributed rest"() {
        given:
        def expectedOutputList = [
                CalculationResult.builder().fundID(1L).amount(1000).percent("10").build(),
                CalculationResult.builder().fundID(2L).amount(1000).percent("10").build(),
                CalculationResult.builder().fundID(3L).amount(2500).percent("25").build(),
                CalculationResult.builder().fundID(4L).amount(2500).percent("25").build(),
                CalculationResult.builder().fundID(5L).amount(2500).percent("25").build(),
                CalculationResult.builder().fundID(6L).amount(500).percent("5").build(),
        ]
        def expectedOutputRest = 1
        when:
        def calculatedDistribution = ic.calculateFundsDistribution(fundList, 10001, new Safe())
        then:
        calculatedDistribution.resultList == expectedOutputList
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
        def expectedOutputList = [
                CalculationResult.builder().fundID(1L).amount(668).percent("6.68").build(),
                CalculationResult.builder().fundID(2L).amount(666).percent("6.66").build(),
                CalculationResult.builder().fundID(3L).amount(666).percent("6.66").build(),
                CalculationResult.builder().fundID(4L).amount(3750).percent("37.5").build(),
                CalculationResult.builder().fundID(5L).amount(3750).percent("37.5").build(),
                CalculationResult.builder().fundID(6L).amount(500).percent("5").build(),
        ]
        def expectedOutputRest = 0
        when:
        def calculatedDistribution = ic.calculateFundsDistribution(fundList, 10000, new Safe())
        then:
        calculatedDistribution.resultList == expectedOutputList
        calculatedDistribution.undistributedRest == expectedOutputRest
    }

}
