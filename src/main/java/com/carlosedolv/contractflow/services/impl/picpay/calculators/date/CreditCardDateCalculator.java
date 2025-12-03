package com.carlosedolv.contractflow.services.impl.picpay.calculators.date;

import com.carlosedolv.contractflow.services.contracts.InstallmentDateCalculator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("creditCardDateCalculator")
public class CreditCardDateCalculator implements InstallmentDateCalculator {
	@Override
    public LocalDate calculate(LocalDate start, int installmentNumber) {
        return start.plusMonths(installmentNumber);
    }
}
