package org.example.investmentfunds.result;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ResultListProducer {

    public List<CalculationResult> createCalculationResultList(List<Long> listOfFundListIDs, List<BigDecimal> percentageDistribution,
                                                               List<Integer> amountDistribution) {
        if (listOfFundListIDs.size() != percentageDistribution.size() || percentageDistribution.size() != amountDistribution.size()) {
            throw new NonEqualArgumentsSizeException("All method arguments should be equal size");
        }

        List<CalculationResult> calculationResultList = new ArrayList<>();
        for (int i = 0; i < listOfFundListIDs.size(); ++i) {
            calculationResultList.add(CalculationResult.builder()
                                                       .fundID(listOfFundListIDs.get(i))
                                                       .amount(amountDistribution.get(i))
                                                       .percent(formatPercentDisplay(percentageDistribution.get(i)))
                                                       .build());
        }
        return calculationResultList;
    }

    private String formatPercentDisplay(BigDecimal numberToFormat) {
        BigDecimal HUNDRED_PERCENT = new BigDecimal("100");
        return numberToFormat.multiply(HUNDRED_PERCENT)
                             .setScale(2, RoundingMode.DOWN)
                             .stripTrailingZeros()
                             .toPlainString();
    }
}
