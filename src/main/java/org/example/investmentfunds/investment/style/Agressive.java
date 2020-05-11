package org.example.investmentfunds.investment.style;

import org.example.investmentfunds.model.FundType;

import java.util.Map;

public class Agressive implements InvestmentStyle {

    @Override
    public Map<FundType, Double> getInvestmentRatioMap() {
        return Map.of(FundType.POLISH, 0.4, FundType.FOREIGN, 0.2, FundType.CASH, 0.4);
    }
}
