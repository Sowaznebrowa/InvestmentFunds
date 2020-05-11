package org.example.investmentfunds.investment.style;

import org.example.investmentfunds.model.FundType;

import java.util.Map;

public class Safe implements InvestmentStyle{

    @Override
    public Map<FundType, Double> getInvestmentRatioMap() {
        return Map.of(FundType.POLISH,0.2,FundType.FOREIGN,0.75,FundType.CASH,0.05);
    }
}
