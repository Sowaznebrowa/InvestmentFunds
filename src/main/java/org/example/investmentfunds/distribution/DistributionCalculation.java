package org.example.investmentfunds.distribution;

import lombok.Builder;
import lombok.Data;
import org.example.investmentfunds.result.CalculationResult;

import java.util.List;

@Data
@Builder
public class DistributionCalculation {

    private List<CalculationResult> resultList;
    private Integer undistributedRest;
}
