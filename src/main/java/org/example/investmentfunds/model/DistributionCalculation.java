package org.example.investmentfunds.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class DistributionCalculation {

    private Map<Long,Integer> distributionMap;
    private Integer undistributedRest;
}
