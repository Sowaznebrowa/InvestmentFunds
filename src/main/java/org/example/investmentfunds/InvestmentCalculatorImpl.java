package org.example.investmentfunds;

import org.example.investmentfunds.investment.style.InvestmentStyle;
import org.example.investmentfunds.model.DistributionCalculation;
import org.example.investmentfunds.model.Fund;
import org.example.investmentfunds.model.FundType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InvestmentCalculatorImpl implements InvestmentCalculator {

    public DistributionCalculation calculateFundsDistribution(List<Fund> listOfFunds, Integer investmentAmount, InvestmentStyle investmentStyle) {

        Map<Long, Integer> distributionMap = new HashMap<>();
        Map<FundType, Double> investmentRatioMap = investmentStyle.getInvestmentRatioMap();

        Map<FundType, Long> fundsGroupedByType = listOfFunds
                .stream()
                .collect(Collectors.groupingBy(Fund::getType, Collectors.counting()));

        listOfFunds.forEach(fund -> {
            distributionMap.put(fund.getId(), calculateFundAmount(investmentRatioMap.get(fund.getType()),
                                                                  fundsGroupedByType.get(fund.getType()),
                                                                  investmentAmount).intValue());
        });

        Integer rest = calculateUndistributedRest(investmentAmount, distributionMap);

        return DistributionCalculation.builder()
                                      .distributionMap(distributionMap)
                                      .undistributedRest(rest)
                                      .build();
    }

    private Integer calculateUndistributedRest(Integer investmentAmount, Map<Long, Integer> distributionMap) {
        Optional<Integer> distributedSum = distributionMap.values()
                                                          .stream()
                                                          .reduce(Integer::sum);
        return investmentAmount - distributedSum.orElse(0);
    }

    private Double calculateFundAmount(Double investmentRatio, Long numberOfFunds, Integer investedAmount) {
        return investedAmount * investmentRatio / numberOfFunds;
    }
}
