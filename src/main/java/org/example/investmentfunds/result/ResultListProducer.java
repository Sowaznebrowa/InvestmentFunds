package org.example.investmentfunds.result;

import java.math.BigDecimal;
import java.util.List;

public interface ResultListProducer {

    List<CalculationResult> createCalculationResultList(List<Long> listOfFundListIDs, List<BigDecimal> percentageDistribution,
                                                        List<Integer> amountDistribution);
}
