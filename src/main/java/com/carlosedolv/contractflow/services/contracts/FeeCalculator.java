package com.carlosedolv.contractflow.services.contracts;

import java.math.BigDecimal;

public interface FeeCalculator {
    BigDecimal applyMonthlyFee(BigDecimal base, int month);
    BigDecimal applyPaymentFee(BigDecimal amount);
}
