package org.example.investmentfunds;

import org.example.investmentfunds.investment.style.InvestmentStyle;
import org.example.investmentfunds.model.Fund;
import org.example.investmentfunds.model.FundType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InvestmentCalculatorImpl implements InvestmentCalculator {

    public Map<Long, Integer> calculateFundsDistribution(List<Fund> listOfFunds, Integer investmentAmount, InvestmentStyle investmentStyle) {
        Map<Long, Integer> resultMap = new HashMap<>();
        Map<FundType, Double> investmentRatioMap = investmentStyle.getInvestmentRatioMap();

        Map<FundType, Long> fundsGroupedByType = listOfFunds
                .stream()
                .collect(Collectors.groupingBy(Fund::getType, Collectors.counting()));

        listOfFunds.forEach(fund -> {
            resultMap.put(fund.getId(), calculateFundAmount(investmentRatioMap.get(fund.getType()),
                                                              fundsGroupedByType.get(fund.getType()),
                                                              investmentAmount).intValue());
        });

        return resultMap;
    }

    private Double calculateFundAmount(Double investmentRatio, Long numberOfFunds, Integer investedAmount){
        return investedAmount * investmentRatio / numberOfFunds;
    }
}
