package com.carlosedolv.contractflow.services.contracts;

public interface FeeCalculator {
	Double applyMonthlyFee(Double base, int month);
	Double applyPaymentFee(Double amount);
}
