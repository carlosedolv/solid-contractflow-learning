package com.carlosedolv.contractflow.services.impl.picpay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.entities.Installment;
import com.carlosedolv.contractflow.repositories.InstallmentRepository;
import com.carlosedolv.contractflow.services.contracts.FeeCalculator;
import com.carlosedolv.contractflow.services.contracts.InstallmentDateCalculator;
import com.carlosedolv.contractflow.services.contracts.OnlinePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class PicPayPaymentService implements OnlinePaymentService {

	private final InstallmentDateCalculator dateCalculator;
	private final FeeCalculator feeCalculator;

	public PicPayPaymentService(InstallmentDateCalculator dateCalculator, FeeCalculator feeCalculator) {
		this.dateCalculator = dateCalculator;
		this.feeCalculator = feeCalculator;
	}

	@Override
	public List<Installment> processInstallments(Contract contract) {
		List<Installment> installments = new ArrayList<>();
        BigDecimal installmentQuantity = BigDecimal.valueOf(contract.getInstallmentQuantity());
		BigDecimal baseInstallment = contract.getTotal().divide(installmentQuantity, 2, RoundingMode.HALF_UP);

		for (int i = 0; i < contract.getInstallmentQuantity(); i++) {
			LocalDate dateInstallment = dateCalculator.calculate(contract.getDate(), i + 1);
			BigDecimal monthlyFee = feeCalculator.applyMonthlyFee(baseInstallment, i + 1);
			BigDecimal paymentFee = feeCalculator.applyPaymentFee(monthlyFee);
			installments.add(new Installment(null, dateInstallment, paymentFee, contract));
		}
		return installments;
	}
}
