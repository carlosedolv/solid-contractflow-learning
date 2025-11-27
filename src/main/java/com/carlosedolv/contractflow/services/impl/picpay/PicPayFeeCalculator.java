package com.carlosedolv.contractflow.services.impl.picpay;

import com.carlosedolv.contractflow.services.contracts.FeeCalculator;

public class PicPayFeeCalculator implements FeeCalculator{

	@Override
	public Double applyMonthlyFee(Double base, int month) {
		return base + month * (base * 0.01);
	}

	@Override
	public Double applyPaymentFee(Double amount) {
		return amount + amount * 0.02;
	}
}
