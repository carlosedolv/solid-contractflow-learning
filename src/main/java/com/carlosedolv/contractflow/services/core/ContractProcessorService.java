package com.carlosedolv.contractflow.services.core;

import java.util.List;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.entities.Installment;
import com.carlosedolv.contractflow.factories.DateCalculatorFactory;
import com.carlosedolv.contractflow.services.ContractService;
import com.carlosedolv.contractflow.services.InstallmentService;
import com.carlosedolv.contractflow.services.contracts.FeeCalculator;
import com.carlosedolv.contractflow.services.contracts.InstallmentDateCalculator;
import com.carlosedolv.contractflow.services.contracts.OnlinePaymentService;
import com.carlosedolv.contractflow.services.impl.picpay.PicPayPaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ContractProcessorService {
    private final DateCalculatorFactory dateCalculatorFactory;
    private final FeeCalculator feeCalculator;
    private final ContractService contractService;
    private final InstallmentService installmentService;

    @Autowired
	public ContractProcessorService(
            DateCalculatorFactory dateCalculatorFactory,
            FeeCalculator feeCalculator,
            ContractService contractService,
            InstallmentService installmentService
    ) {
        this.dateCalculatorFactory = dateCalculatorFactory;
        this.feeCalculator = feeCalculator;
        this.contractService = contractService;
        this.installmentService = installmentService;
	}

    @Transactional
	public Contract processContract(Contract contract) {
        try {
            InstallmentDateCalculator dateCalculator = dateCalculatorFactory.getCalculator(contract.getPaymentType());
            OnlinePaymentService paymentService = new PicPayPaymentService(dateCalculator, feeCalculator);
            List<Installment> installments = paymentService.processInstallments(contract);
            contract.setInstallments(installments);
            for(Installment installment : installments) {
                installmentService.insert(installment);
            }
        } catch (IllegalAccessException e) {
            System.err.println(e.getMessage());
        }
		return contractService.insert(contract);
	}
}
