package org.example.investmentfunds.investment;

import lombok.AllArgsConstructor;
import org.example.investmentfunds.InvestmentCalculator;
import org.example.investmentfunds.distribution.DistributionCalculator;
import org.example.investmentfunds.fund.Fund;
import org.example.investmentfunds.fund.FundFilter;
import org.example.investmentfunds.investment.style.InvestmentStyle;
import org.example.investmentfunds.result.CalculationResult;
import org.example.investmentfunds.result.ResultListProducer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class DistributionBasedInvestmentCalculator implements InvestmentCalculator {

    private DistributionCalculator distributable;
    private ResultListProducer resultListProducer;

    public InvestmentCalculation calculateInvestment(List<Fund> listOfFunds, Integer investmentAmount, InvestmentStyle investmentStyle) {

        List<CalculationResult> calculationResults = new ArrayList<>();
        investmentStyle.getFundTypeSet()
                       .forEach(fundType -> {
                           List<Fund> funds = FundFilter.filterFundsByType(listOfFunds, fundType);
                           List<BigDecimal> percentageDistribution = distributable.calculatePercentageDistribution(
                                   funds.size(),
                                   investmentStyle.getInvestmentRatioByType(fundType));
                           List<Integer> amountDistribution = distributable.calculateAmountDistribution(percentageDistribution,
                                                                                                        investmentAmount);
                           calculationResults.addAll(
                                   resultListProducer.createCalculationResultList(FundFilter.getListOfIDs(funds), percentageDistribution,
                                                                                  amountDistribution));
                       });

        calculationResults.sort(Comparator.comparing(CalculationResult::getFundID));
        Integer rest = distributable.calculateUndistributedRest(investmentAmount, calculationResults);

        return InvestmentCalculation.builder()
                                    .resultList(calculationResults)
                                    .undistributedRest(rest)
                                    .build();
    }

}
