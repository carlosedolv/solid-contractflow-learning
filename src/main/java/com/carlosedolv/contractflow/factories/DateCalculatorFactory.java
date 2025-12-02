package com.carlosedolv.contractflow.factories;

import com.carlosedolv.contractflow.entities.enums.PaymentType;
import com.carlosedolv.contractflow.services.contracts.InstallmentDateCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class DateCalculatorFactory {
    private final Map<PaymentType, InstallmentDateCalculator> calculators;

    public DateCalculatorFactory(
            @Qualifier("creditCardDateCalculator") InstallmentDateCalculator creditCardCalc,
            @Qualifier("debitCardDateCalculator") InstallmentDateCalculator debitCardCalc,
            @Qualifier("pixDateCalculator") InstallmentDateCalculator pixCalc
    ) {
        this.calculators = new EnumMap<>(PaymentType.class);
        calculators.put(PaymentType.CREDIT,  creditCardCalc);
        calculators.put(PaymentType.DEBIT,  debitCardCalc);
        calculators.put(PaymentType.PIX,  pixCalc);
    }

    public InstallmentDateCalculator getCalculator(PaymentType paymentType) {
        InstallmentDateCalculator calculator = calculators.get(paymentType);
        if(calculator == null) {
            throw new IllegalArgumentException("No calculator for payment type: " + paymentType);
        }
        return calculator;
    }
}
