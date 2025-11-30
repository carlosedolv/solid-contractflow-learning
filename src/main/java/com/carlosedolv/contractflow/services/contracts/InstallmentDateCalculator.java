package com.carlosedolv.contractflow.services.contracts;

import java.time.LocalDate;

public interface InstallmentDateCalculator {
	LocalDate calculate(LocalDate start, int installmentNumber);
}
