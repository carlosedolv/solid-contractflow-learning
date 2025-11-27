package com.carlosedolv.contractflow.services.impl.picpay;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.entities.Installment;
import com.carlosedolv.contractflow.services.contracts.FeeCalculator;
import com.carlosedolv.contractflow.services.contracts.InstallmentDateService;
import com.carlosedolv.contractflow.services.contracts.OnlinePaymentService;

public class PicPayPaymentService implements OnlinePaymentService {

	private final InstallmentDateService dateService;
	private final FeeCalculator feeCalculator;

	public PicPayPaymentService(InstallmentDateService dateService, FeeCalculator feeCalculator) {
		this.dateService = dateService;
		this.feeCalculator = feeCalculator;
	}

	@Override
	public List<Installment> processInstallments(Contract contract) {
		List<Installment> installments = new ArrayList<>();
		Double baseInstallment = contract.getTotal() / contract.getInstallmentQuantity();

		for (int i = 0; i < contract.getInstallmentQuantity(); i++) {
			Instant dateInstallment = dateService.calculate(contract.getDate(), i + 1);
			Double monthlyFee = feeCalculator.applyMonthlyFee(baseInstallment, i + 1);
			Double paymentFee = feeCalculator.applyPaymentFee(monthlyFee);

			installments.add(new Installment(null, dateInstallment, paymentFee, contract));
		}
		return installments;
	}

}
