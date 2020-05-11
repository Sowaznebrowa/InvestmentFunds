package org.example.investmentfunds.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fund {

    Long id;
    String name;
    FundType type;
}
