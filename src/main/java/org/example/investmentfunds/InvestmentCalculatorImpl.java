package org.example.investmentfunds;

import org.example.investmentfunds.investment.style.InvestmentStyle;
import org.example.investmentfunds.model.AmountPercentPair;
import org.example.investmentfunds.model.DistributionCalculation;
import org.example.investmentfunds.model.Fund;
import org.example.investmentfunds.model.FundType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InvestmentCalculatorImpl implements InvestmentCalculator {

    public DistributionCalculation calculateFundsDistribution(List<Fund> listOfFunds, Integer investmentAmount, InvestmentStyle investmentStyle) {
        Map<FundType, BigDecimal> investmentRatioMap = investmentStyle.getInvestmentRatioMap();
        List<AmountPercentPair> amountPercentPairsList = calculateAmountPercentPairs(listOfFunds, investmentAmount, investmentRatioMap);
        Integer rest = calculateUndistributedRest(investmentAmount, amountPercentPairsList);
        Map<Long, AmountPercentPair> distributionMap = amountPercentPairsList.stream()
                                                                             .collect(Collectors.toMap(AmountPercentPair::getFundID,
                                                                                                       amountPercentPair -> amountPercentPair));
        return DistributionCalculation.builder()
                                      .distributionMap(distributionMap)
                                      .undistributedRest(rest)
                                      .build();
    }

    private List<AmountPercentPair> calculateAmountPercentPairs(List<Fund> listOfFunds, Integer investmentAmount, Map<FundType, BigDecimal> investmentRatioMap) {
        Map<FundType, List<Fund>> fundsGroupedByType = listOfFunds
                .stream()
                .collect(Collectors.groupingBy(Fund::getType));

        List<AmountPercentPair> amountPercentPairsList = new ArrayList<>();

        fundsGroupedByType.keySet()
                          .forEach(key -> amountPercentPairsList.addAll(
                                  distributeListOfFunds(fundsGroupedByType.get(key), investmentAmount, investmentRatioMap.get(key))));
        return amountPercentPairsList;
    }

    private List<AmountPercentPair> distributeListOfFunds(List<Fund> funds, Integer investmentAmount, BigDecimal investmentRatio) {
        if (funds.isEmpty()) {
            return new ArrayList<>();
        }
        BigDecimal HUNDRED_PERCENT = new BigDecimal("100");
        int FIRST_ELEMENT = 0;
        int listSize = funds.size();
        BigDecimal availableAmount = BigDecimal.valueOf(investmentAmount);
        BigDecimal singleFundRatio = investmentRatio.divide(BigDecimal.valueOf(listSize), 4, RoundingMode.DOWN);
        List<AmountPercentPair> distributionList = new ArrayList<>();
        funds.forEach(fund -> distributionList.add(AmountPercentPair.builder()
                                                                .fundID(fund.getId())
                                                                .amount(availableAmount.multiply(singleFundRatio).intValue())
                                                                .percent(formatPercentDisplay(singleFundRatio.multiply(HUNDRED_PERCENT)))
                                                                .build()));
        if (!singleFundRatio.multiply(BigDecimal.valueOf(listSize))
                            .equals(investmentRatio)) {
            BigDecimal delta = investmentRatio.subtract(singleFundRatio.multiply(BigDecimal.valueOf(listSize)));
            BigDecimal enlargedSingleFundRatio = singleFundRatio.add(delta);
            distributionList.get(FIRST_ELEMENT)
                            .setAmount(enlargedSingleFundRatio.multiply(availableAmount)
                                                              .intValue());
            distributionList.get(FIRST_ELEMENT)
                            .setPercent(formatPercentDisplay(enlargedSingleFundRatio.multiply(HUNDRED_PERCENT)));
        }
        return distributionList;
    }

    private Integer calculateUndistributedRest(Integer investmentAmount, List<AmountPercentPair> amountPercentPairList) {
        Integer distributedSum = amountPercentPairList
                .stream()
                .map(AmountPercentPair::getAmount)
                .reduce(Integer::sum)
                .orElse(0);
        return investmentAmount - distributedSum;
    }

    private String formatPercentDisplay(BigDecimal numberToFormat){
        return numberToFormat.setScale(2, RoundingMode.DOWN).stripTrailingZeros().toPlainString();
    }

}
