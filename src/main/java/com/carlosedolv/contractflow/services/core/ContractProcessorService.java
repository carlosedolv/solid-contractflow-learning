package com.carlosedolv.contractflow.services.core;

import java.util.List;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.entities.Installment;
import com.carlosedolv.contractflow.entities.enums.PaymentType;
import com.carlosedolv.contractflow.exceptions.InvalidPaymentMethodException;
import com.carlosedolv.contractflow.factories.DateCalculatorFactory;
import com.carlosedolv.contractflow.services.ContractService;
import com.carlosedolv.contractflow.services.InstallmentService;
import com.carlosedolv.contractflow.services.contracts.FeeCalculator;
import com.carlosedolv.contractflow.services.contracts.InstallmentDateCalculator;
import com.carlosedolv.contractflow.services.contracts.OnlinePaymentService;
import com.carlosedolv.contractflow.services.impl.picpay.PicPayPaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractProcessorService {
    private final DateCalculatorFactory dateCalculatorFactory;
    private final FeeCalculator feeCalculator;
    private final ContractService service;

    @Autowired
	public ContractProcessorService(
            DateCalculatorFactory dateCalculatorFactory,
            FeeCalculator feeCalculator,
            InstallmentService installmentService,
            ContractService service
    ) {
        this.dateCalculatorFactory = dateCalculatorFactory;
        this.feeCalculator = feeCalculator;
        this.service = service;
    }

    @Transactional
	public Contract processContract(Contract contract) {
        validatePaymentRules(contract);
        InstallmentDateCalculator dateCalculator = dateCalculatorFactory.getCalculator(contract.getPaymentType());
        OnlinePaymentService paymentService = new PicPayPaymentService(dateCalculator, feeCalculator);
        List<Installment> installments = paymentService.processInstallments(contract);
        contract.setInstallments(installments);
        return service.insert(contract);
	}

    private void validatePaymentRules(Contract contract) {
        if(contract.getPaymentType().equals(PaymentType.PIX)) {
            if(contract.getInstallmentQuantity() != 1) throw new InvalidPaymentMethodException(
                    "Contract " + contract.getNumber() + ": Invalid installment quantity for pix method."
            );
        }
    }
}
