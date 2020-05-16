package org.example.investmentfunds.investment.style;

import org.example.investmentfunds.fund.FundType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class Safe implements InvestmentStyle {

    private Map<FundType, BigDecimal> investmentRatioMap = Map.of(FundType.POLISH, new BigDecimal("0.20"),
                                                                  FundType.FOREIGN, new BigDecimal("0.75"),
                                                                  FundType.CASH, new BigDecimal("0.05"));

    @Override
    public BigDecimal getInvestmentRatioByType(FundType fundType) {
        return investmentRatioMap.get(fundType);
    }

    @Override
    public Set<FundType> getFundTypeSet() {
        return investmentRatioMap.keySet();
    }
}
