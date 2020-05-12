package org.example.investmentfunds;

import org.example.investmentfunds.investment.style.InvestmentStyle;
import org.example.investmentfunds.model.DistributionCalculation;
import org.example.investmentfunds.model.Fund;

import java.util.List;

public interface InvestmentCalculator {

    DistributionCalculation calculateFundsDistribution(List<Fund> listOfFunds, Integer investmentAmount, InvestmentStyle investmentStyle);

}
