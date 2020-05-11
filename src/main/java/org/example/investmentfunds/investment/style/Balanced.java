package org.example.investmentfunds.investment.style;

import org.example.investmentfunds.model.FundType;

import java.util.Map;

public class Balanced implements InvestmentStyle {

    //TODO: change double to big decimal
    @Override
    public Map<FundType, Double> getInvestmentRatioMap() {
        return Map.of(FundType.POLISH, 0.3, FundType.FOREIGN, 0.6, FundType.CASH, 0.1);
    }
}
