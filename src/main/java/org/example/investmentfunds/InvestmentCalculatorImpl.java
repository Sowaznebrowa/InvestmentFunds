package org.example.investmentfunds;

import org.example.investmentfunds.distribution.DistributionCalculation;
import org.example.investmentfunds.distribution.DistributionCalculator;
import org.example.investmentfunds.fund.Fund;
import org.example.investmentfunds.fund.FundType;
import org.example.investmentfunds.investment.style.InvestmentStyle;
import org.example.investmentfunds.result.CalculationResult;
import org.example.investmentfunds.result.ResultListProducer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InvestmentCalculatorImpl implements InvestmentCalculator {

    private DistributionCalculator distributionCalculator;
    private ResultListProducer resultListProducer;

    public InvestmentCalculatorImpl(DistributionCalculator distributionCalculator, ResultListProducer resultListProducer) {
        this.distributionCalculator = distributionCalculator;
        this.resultListProducer = resultListProducer;
    }

    public DistributionCalculation calculateFundsDistribution(List<Fund> listOfFunds, Integer investmentAmount, InvestmentStyle investmentStyle) {

        List<CalculationResult> calculationResults = new ArrayList<>();
        for (FundType fundType : investmentStyle.getFundTypeSet()) {
            List<Fund> funds = filterFundsByType(listOfFunds, fundType);
            List<BigDecimal> percentageDistribution = distributionCalculator.calculatePercentageDistribution(
                    funds.size(),
                    investmentStyle.getInvestmentRatioByType(fundType));
            List<Integer> amountDistribution = distributionCalculator.calculateAmountDistribution(percentageDistribution, investmentAmount);
            calculationResults.addAll(
                    resultListProducer.createCalculationResultList(getListOfIDsFromFundList(funds), percentageDistribution, amountDistribution));
        }
        calculationResults.sort(Comparator.comparing(CalculationResult::getFundID));
        Integer rest = distributionCalculator.calculateUndistributedRest(investmentAmount, calculationResults);
        return DistributionCalculation.builder()
                                      .resultList(calculationResults)
                                      .undistributedRest(rest)
                                      .build();
    }

    private List<Long> getListOfIDsFromFundList(List<Fund> listOfFunds) {
        return listOfFunds.stream()
                          .map(Fund::getId)
                          .collect(Collectors.toList());
    }

    private List<Fund> filterFundsByType(List<Fund> listOfFunds, FundType fundType) {
        return listOfFunds.stream()
                          .filter(fund -> fund.getType()
                                              .equals(fundType))
                          .collect(Collectors.toList());
    }


}
