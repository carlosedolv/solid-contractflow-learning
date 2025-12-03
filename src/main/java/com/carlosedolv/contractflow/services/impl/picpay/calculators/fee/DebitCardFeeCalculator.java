package com.carlosedolv.contractflow.services.impl.picpay.calculators.fee;

import com.carlosedolv.contractflow.services.contracts.FeeCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("debitCardFeeCalculator")
public class DebitCardFeeCalculator implements FeeCalculator{

    @Override
    public BigDecimal applyMonthlyFee(BigDecimal base, int month) {
        // Reduced monthly rate for installment payments (0.5%)
        BigDecimal monthlyRate = new BigDecimal("0.005");
        BigDecimal monthlyFee = monthlyRate.multiply(base);
        return BigDecimal.valueOf(month).multiply(monthlyFee).add(base);
    }


    @Override
    public BigDecimal applyPaymentFee(BigDecimal amount) {
        BigDecimal limitValue = new BigDecimal("500.0");
        BigDecimal feeRate = new BigDecimal("0.01");
        // If the value is less than or equal to 500, no fee applies.
        if (amount.compareTo(limitValue) <= 0) {
            return amount;
        }
        // If the value is greater than 500, an initial fee applies.
        BigDecimal paymentFee = feeRate.multiply(amount);
        return amount.add(paymentFee);
    }

}
