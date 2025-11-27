package com.carlosedolv.contractflow.services.contracts;

import java.time.Instant;

public interface InstallmentDateService {
	Instant calculate(Instant start, int installmentNumber);
}
