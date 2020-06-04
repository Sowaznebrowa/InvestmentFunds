package org.example.investmentfunds;

import org.example.investmentfunds.fund.Fund;
import org.example.investmentfunds.investment.InvestmentCalculation;
import org.example.investmentfunds.investment.style.InvestmentStyle;
import org.example.investmentfunds.result.CalculationResult;

import java.math.BigInteger;
import java.util.List;

/**
 * Investment Calculator API
 */
public interface InvestmentCalculator {

    /**
     * Produces {@link InvestmentCalculation} from provided list of {@link Fund}, investment amount and {@link InvestmentStyle}.
     *
     * @param listOfFunds      List of {@link Fund} on which we want to distribute the investment amount.
     * @param investmentAmount Investment amount available to distribute on all funds.
     * @param investmentStyle  Object which implements {@link InvestmentStyle} interface.
     * @return {@link InvestmentCalculation} object, which contains undistributed rest of investment amount
     * and list of {@link CalculationResult} with percentage distribution and amount distribution.
     */
    InvestmentCalculation calculateInvestment(List<Fund> listOfFunds, BigInteger investmentAmount, InvestmentStyle investmentStyle);

}
