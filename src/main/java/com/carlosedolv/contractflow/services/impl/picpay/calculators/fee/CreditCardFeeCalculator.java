package com.carlosedolv.contractflow.services.impl.picpay.calculators.fee;

import com.carlosedolv.contractflow.services.contracts.FeeCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("creditCardFeeCalculator")
public class CreditCardFeeCalculator implements FeeCalculator{

	@Override
	public BigDecimal applyMonthlyFee(BigDecimal base, int month) {
        BigDecimal feeRate = new BigDecimal("0.01");
        BigDecimal monthlyFee = feeRate.multiply(base);
		return BigDecimal.valueOf(month).multiply(monthlyFee).add(base);
	}

	@Override
	public BigDecimal applyPaymentFee(BigDecimal amount) {
        BigDecimal feeRate = new BigDecimal("0.02");
        BigDecimal paymentFee = feeRate.multiply(amount);
		return amount.add(paymentFee);
	}
}
