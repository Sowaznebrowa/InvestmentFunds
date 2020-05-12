package org.example.investmentfunds.investment.style;

import org.example.investmentfunds.model.FundType;

import java.math.BigDecimal;
import java.util.Map;

public class Safe implements InvestmentStyle {

    @Override
    public Map<FundType, BigDecimal> getInvestmentRatioMap() {
        return Map.of(FundType.POLISH, new BigDecimal("0.20"),
                      FundType.FOREIGN, new BigDecimal("0.75"),
                      FundType.CASH, new BigDecimal("0.05"));
    }
}
