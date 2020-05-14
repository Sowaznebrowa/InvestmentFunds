package org.example.investmentfunds;

import org.example.investmentfunds.investment.style.InvestmentStyle;
import org.example.investmentfunds.distribution.DistributionCalculation;
import org.example.investmentfunds.fund.Fund;

import java.util.List;

public interface InvestmentCalculator {

    DistributionCalculation calculateFundsDistribution(List<Fund> listOfFunds, Integer investmentAmount, InvestmentStyle investmentStyle);

}
