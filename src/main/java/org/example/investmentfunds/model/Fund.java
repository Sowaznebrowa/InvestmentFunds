package org.example.investmentfunds.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fund {

    private Long id;
    private String name;
    private FundType type;
}
