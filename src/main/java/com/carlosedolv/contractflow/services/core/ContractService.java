package com.carlosedolv.contractflow.services.core;

import java.util.List;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.entities.Installment;
import com.carlosedolv.contractflow.services.contracts.OnlinePaymentService;

public class ContractService {
	private final OnlinePaymentService paymentService;

	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public Contract processContract(Contract contract) {
		List<Installment> installments = paymentService.processInstallments(contract);
		contract.setInstallments(installments);
		return contract;
	}
}
