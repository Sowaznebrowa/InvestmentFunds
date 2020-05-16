package org.example.investmentfunds.fund;

import java.util.List;
import java.util.stream.Collectors;

public class FundFilter {

    public static List<Long> getListOfIDs(List<Fund> listOfFunds) {
        return listOfFunds.stream()
                          .map(Fund::getId)
                          .collect(Collectors.toList());
    }

    public static List<Fund> filterFundsByType(List<Fund> listOfFunds, FundType fundType) {
        return listOfFunds.stream()
                          .filter(fund -> fund.getType()
                                              .equals(fundType))
                          .collect(Collectors.toList());
    }
}
