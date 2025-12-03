package com.carlosedolv.contractflow.factories;

import com.carlosedolv.contractflow.entities.enums.PaymentType;
import com.carlosedolv.contractflow.services.contracts.FeeCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class FeeCalculatorFactory {
    private final Map<PaymentType, FeeCalculator> calculators;

    public FeeCalculatorFactory(
            @Qualifier("creditCardFeeCalculator") FeeCalculator creditCardCalc,
            @Qualifier("debitCardFeeCalculator") FeeCalculator debitCardCalc,
            @Qualifier("pixFeeCalculator") FeeCalculator pixCalc
    ) {
        this.calculators = new EnumMap<>(PaymentType.class);
        calculators.put(PaymentType.CREDIT, creditCardCalc);
        calculators.put(PaymentType.DEBIT, debitCardCalc);
        calculators.put(PaymentType.PIX, pixCalc);
    }

    public FeeCalculator getCalculator(PaymentType paymentType) {
        FeeCalculator calculator = calculators.get(paymentType);
        if(calculator == null) {
            throw new IllegalArgumentException("No calculator for payment type: " + paymentType);
        }
        return calculator;
    }
}
