package org.example.investmentfunds.investment.style;

import org.example.investmentfunds.fund.FundType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public interface InvestmentStyle {

    Map<FundType, BigDecimal> getInvestmentRatioMap();
    BigDecimal getInvestmentRatioByType(FundType fundType);
    Set<FundType> getFundTypeSet();
}
