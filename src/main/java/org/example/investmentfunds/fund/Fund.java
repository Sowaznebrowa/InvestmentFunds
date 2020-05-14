package org.example.investmentfunds.fund;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fund {

    private Long id;
    private String name;
    private FundType type;
}
