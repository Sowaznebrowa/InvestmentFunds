package org.example.investmentfunds

import org.example.investmentfunds.distribution.AmountDistributionCalculator
import org.example.investmentfunds.distribution.PercentageDistributionCalculator
import org.example.investmentfunds.distribution.UndistributedRestCalculator
import org.example.investmentfunds.fund.Fund
import org.example.investmentfunds.fund.FundType
import org.example.investmentfunds.investment.DistributionBasedInvestmentCalculator
import org.example.investmentfunds.investment.style.Agressive
import org.example.investmentfunds.investment.style.Balanced
import org.example.investmentfunds.investment.style.Safe
import org.example.investmentfunds.result.ResultListProducer
import spock.lang.Specification

class TestBase extends Specification{

    def fundsList = [
            Fund.builder().id(1).type(FundType.POLISH).name("Fundusz Polski 1").build(),
            Fund.builder().id(2).type(FundType.POLISH).name("Fundusz Polski 2").build(),
            Fund.builder().id(3).type(FundType.FOREIGN).name("Fundusz Zagraniczny 1").build(),
            Fund.builder().id(4).type(FundType.FOREIGN).name("Fundusz Zagraniczny 2").build(),
            Fund.builder().id(5).type(FundType.FOREIGN).name("Fundusz Zagraniczny 3").build(),
            Fund.builder().id(6).type(FundType.CASH).name("Fundusz Pieniężny 1").build(),
    ]
    def safeInvestmentStyle = new Safe()
    def agressiveInvestmentStyle = new Agressive()
    def balancedInvestmentStyle = new Balanced()

    PercentageDistributionCalculator percentageDistributionCalculator = new PercentageDistributionCalculator()
    AmountDistributionCalculator amountDistributionCalculator = new AmountDistributionCalculator()
    UndistributedRestCalculator undistributedRestCalculator = new UndistributedRestCalculator()

    ResultListProducer resultListProducer = new ResultListProducer()
    InvestmentCalculator investmentCalculator = new DistributionBasedInvestmentCalculator(
            percentageDistributionCalculator,
            amountDistributionCalculator,
            undistributedRestCalculator,
            resultListProducer)

}
