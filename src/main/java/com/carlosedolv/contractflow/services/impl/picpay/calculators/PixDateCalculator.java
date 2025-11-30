package com.carlosedolv.contractflow.services.impl.picpay.calculators;

import com.carlosedolv.contractflow.services.contracts.InstallmentDateCalculator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("pixDateCalculator")
public class PixDateCalculator implements InstallmentDateCalculator {
	@Override
	public LocalDate calculate(LocalDate start, int installmentNumber) {
		return start.plusDays(1L);
	}
}
