package org.example.investmentfunds;

import org.example.investmentfunds.investment.style.InvestmentStyle;
import org.example.investmentfunds.model.Fund;

import java.util.List;
import java.util.Map;

public interface InvestmentCalculator {

    Map<Long, Integer> calculateFundsDistribution(List<Fund> listOfFunds, Integer investmentAmount, InvestmentStyle investmentStyle);

}
