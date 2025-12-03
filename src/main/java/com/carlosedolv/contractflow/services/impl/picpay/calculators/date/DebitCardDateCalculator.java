package com.carlosedolv.contractflow.services.impl.picpay.calculators.date;

import java.time.LocalDate;

import com.carlosedolv.contractflow.services.contracts.InstallmentDateCalculator;
import org.springframework.stereotype.Component;

@Component("debitCardDateCalculator")
public class DebitCardDateCalculator implements InstallmentDateCalculator {
	@Override
    public LocalDate calculate(LocalDate start, int installmentNumber) {
        if (installmentNumber == 1) {
            return start;
        }
        return start.plusDays((installmentNumber - 1) * 21L);
    }
}
