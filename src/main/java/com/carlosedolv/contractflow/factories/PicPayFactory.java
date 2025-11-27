package com.carlosedolv.contractflow.factories;

import com.carlosedolv.contractflow.controllers.ContractController;
import com.carlosedolv.contractflow.services.contracts.FeeCalculator;
import com.carlosedolv.contractflow.services.contracts.InstallmentDateService;
import com.carlosedolv.contractflow.services.contracts.OnlinePaymentService;
import com.carlosedolv.contractflow.services.core.ContractService;
import com.carlosedolv.contractflow.services.impl.picpay.PicPayFeeCalculator;
import com.carlosedolv.contractflow.services.impl.picpay.PicPayInstallmentDateService;
import com.carlosedolv.contractflow.services.impl.picpay.PicPayPaymentService;

public class PicPayFactory {

	private static InstallmentDateService createInstallmentDateService() {
		return new PicPayInstallmentDateService();
	}

	private static FeeCalculator createFeeCalculator() {
		return new PicPayFeeCalculator();
	}

	private static OnlinePaymentService createOnlinePaymentService() {
		return new PicPayPaymentService(createInstallmentDateService(), createFeeCalculator());
	}

	private static ContractService createContractService() {
		return new ContractService(createOnlinePaymentService());
	}

	public static ContractController createContractController() {
		return new ContractController(createContractService());
	}
}
