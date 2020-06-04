package org.example.investmentfunds.investment

import org.example.investmentfunds.TestBase
import org.example.investmentfunds.fund.Fund
import org.example.investmentfunds.fund.FundType
import org.example.investmentfunds.result.CalculationResult

class DistributionBasedInvestmentCalculatorTest extends TestBase {

    def "should return investment calculation for safe style"() {
        given:
        def expectedInvestmentCalculation = InvestmentCalculation.builder()
                .resultList([
                        CalculationResult.builder().fundID(1L).amount("1000").percent("10%").build(),
                        CalculationResult.builder().fundID(2L).amount("1000").percent("10%").build(),
                        CalculationResult.builder().fundID(3L).amount("2500").percent("25%").build(),
                        CalculationResult.builder().fundID(4L).amount("2500").percent("25%").build(),
                        CalculationResult.builder().fundID(5L).amount("2500").percent("25%").build(),
                        CalculationResult.builder().fundID(6L).amount("500").percent("5%").build(),
                ])
                .undistributedRest(BigInteger.ZERO)
                .build()
        when:
        def calculatedInvestment = investmentCalculator.calculateInvestment(
                fundsList,
                new BigInteger("10000"),
                safeInvestmentStyle)
        then:
        calculatedInvestment == expectedInvestmentCalculation

    }

    def "should return investment calculation for agressive style"() {
        given:
        def expectedInvestmentCalculation = InvestmentCalculation.builder().resultList([
                CalculationResult.builder().fundID(1L).amount("2000").percent("20%").build(),
                CalculationResult.builder().fundID(2L).amount("2000").percent("20%").build(),
                CalculationResult.builder().fundID(3L).amount("668").percent("6.68%").build(),
                CalculationResult.builder().fundID(4L).amount("666").percent("6.66%").build(),
                CalculationResult.builder().fundID(5L).amount("666").percent("6.66%").build(),
                CalculationResult.builder().fundID(6L).amount("4000").percent("40%").build(),
        ]).undistributedRest(BigInteger.ZERO).build()
        when:
        def calculatedInvestment = investmentCalculator.calculateInvestment(
                fundsList,
                new BigInteger("10000"),
                agressiveInvestmentStyle)
        then:
        calculatedInvestment == expectedInvestmentCalculation
    }

    def "should return investment calculation for balanced style"() {
        given:
        def expectedInvestmentCalculation = InvestmentCalculation.builder().resultList([
                CalculationResult.builder().fundID(1L).amount("1500").percent("15%").build(),
                CalculationResult.builder().fundID(2L).amount("1500").percent("15%").build(),
                CalculationResult.builder().fundID(3L).amount("2000").percent("20%").build(),
                CalculationResult.builder().fundID(4L).amount("2000").percent("20%").build(),
                CalculationResult.builder().fundID(5L).amount("2000").percent("20%").build(),
                CalculationResult.builder().fundID(6L).amount("1000").percent("10%").build(),
        ]).undistributedRest(BigInteger.ZERO).build()
        when:
        def calculatedInvestment = investmentCalculator.calculateInvestment(fundsList, new BigInteger("10000"), balancedInvestmentStyle)
        then:
        calculatedInvestment == expectedInvestmentCalculation
    }

    def "should return investment calculation with undistributed rest"() {
        given:
        def expectedInvestmentCalculation = InvestmentCalculation.builder().resultList([
                CalculationResult.builder().fundID(1L).amount("1000").percent("10%").build(),
                CalculationResult.builder().fundID(2L).amount("1000").percent("10%").build(),
                CalculationResult.builder().fundID(3L).amount("2500").percent("25%").build(),
                CalculationResult.builder().fundID(4L).amount("2500").percent("25%").build(),
                CalculationResult.builder().fundID(5L).amount("2500").percent("25%").build(),
                CalculationResult.builder().fundID(6L).amount("500").percent("5%").build(),
        ]).undistributedRest(BigInteger.ONE).build()
        when:
        def calculatedInvestment = investmentCalculator.calculateInvestment(fundsList, new BigInteger("10001"), safeInvestmentStyle)
        then:
        calculatedInvestment == expectedInvestmentCalculation
    }

    def "should return investment calculation with 2 digits precision percentage"() {
        given:
        def fundList = [
                Fund.builder().id(1).type(FundType.POLISH).name("Fundusz Polski 1").build(),
                Fund.builder().id(2).type(FundType.POLISH).name("Fundusz Polski 2").build(),
                Fund.builder().id(3).type(FundType.POLISH).name("Fundusz Polski 3").build(),
                Fund.builder().id(4).type(FundType.FOREIGN).name("Fundusz Zagraniczny 1").build(),
                Fund.builder().id(5).type(FundType.FOREIGN).name("Fundusz Zagraniczny 2").build(),
                Fund.builder().id(6).type(FundType.CASH).name("Fundusz Pieniężny 1").build(),
        ]
        def expectedInvestmentCalculation = InvestmentCalculation.builder().resultList([
                CalculationResult.builder().fundID(1L).amount("668").percent("6.68%").build(),
                CalculationResult.builder().fundID(2L).amount("666").percent("6.66%").build(),
                CalculationResult.builder().fundID(3L).amount("666").percent("6.66%").build(),
                CalculationResult.builder().fundID(4L).amount("3750").percent("37.5%").build(),
                CalculationResult.builder().fundID(5L).amount("3750").percent("37.5%").build(),
                CalculationResult.builder().fundID(6L).amount("500").percent("5%").build(),
        ]).undistributedRest(BigInteger.ZERO).build()
        when:
        def calculatedInvestment = investmentCalculator.calculateInvestment(fundList, new BigInteger("10000"), safeInvestmentStyle)
        then:
        calculatedInvestment == expectedInvestmentCalculation
    }

}
