package org.example.investmentfunds.investment;

import lombok.AllArgsConstructor;
import org.example.investmentfunds.InvestmentCalculator;
import org.example.investmentfunds.distribution.AmountDistributionCalculator;
import org.example.investmentfunds.distribution.PercentageDistributionCalculator;
import org.example.investmentfunds.distribution.UndistributedRestCalculator;
import org.example.investmentfunds.fund.Fund;
import org.example.investmentfunds.fund.FundFilter;
import org.example.investmentfunds.investment.style.InvestmentStyle;
import org.example.investmentfunds.result.CalculationResult;
import org.example.investmentfunds.result.ResultListProducer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class DistributionBasedInvestmentCalculator implements InvestmentCalculator {

    private final PercentageDistributionCalculator distributionCalculator;
    private final AmountDistributionCalculator amountDistributionCalculator;
    private final UndistributedRestCalculator undistributedRestCalculator;
    private final ResultListProducer resultListProducer;

    public InvestmentCalculation calculateInvestment(List<Fund> listOfFunds, BigInteger investmentAmount, InvestmentStyle investmentStyle) {

        //TODO: simplify this lambda
        List<CalculationResult> calculationResults = new ArrayList<>();
        investmentStyle.getFundTypeSet()
                .forEach(fundType -> {
                    List<Fund> funds = FundFilter.filterFundsByType(listOfFunds, fundType);
                    List<BigDecimal> percentageDistribution = distributionCalculator.calculatePercentageDistribution(
                            funds.size(),
                            investmentStyle.getInvestmentRatioByType(fundType));
                    List<BigDecimal> amountDistribution = amountDistributionCalculator.calculateAmountDistribution(percentageDistribution,
                            investmentAmount);
                    calculationResults.addAll(resultListProducer.createCalculationResultList(FundFilter.getListOfIDs(funds), percentageDistribution, amountDistribution));
                });

        calculationResults.sort(Comparator.comparing(CalculationResult::getFundID));
        BigInteger rest = undistributedRestCalculator.calculateUndistributedRest(investmentAmount, calculationResults);

        return InvestmentCalculation.builder()
                .resultList(calculationResults)
                .undistributedRest(rest)
                .build();
    }

}
