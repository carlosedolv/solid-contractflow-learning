package com.carlosedolv.contractflow.services.impl.picpay.calculators.fee;

import com.carlosedolv.contractflow.services.contracts.FeeCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("pixFeeCalculator")
public class PixFeeCalculator implements FeeCalculator{

	@Override
	public BigDecimal applyMonthlyFee(BigDecimal base, int month) {
        BigDecimal feeRate = new BigDecimal("5.0");
		return base.add(feeRate.multiply(BigDecimal.valueOf(month)));
	}

	@Override
	public BigDecimal applyPaymentFee(BigDecimal amount) {
        BigDecimal limitValue = new BigDecimal("500.0");
        BigDecimal feeRate = new BigDecimal("0.02");
        // Payments up to R$ 500.00 have no fee.
        return (amount.compareTo(limitValue) > 0) ? amount.add(amount.multiply(feeRate)) : amount;
	}
}
