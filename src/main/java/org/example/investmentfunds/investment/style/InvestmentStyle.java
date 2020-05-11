package org.example.investmentfunds.investment.style;

import org.example.investmentfunds.model.FundType;

import java.util.Map;

public interface InvestmentStyle {

    Map<FundType, Double> getInvestmentRatioMap();
}
