package org.example.investmentfunds.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AmountPercentPair {

    private Long fundID;
    private Integer amount;
    private String percent;
}
