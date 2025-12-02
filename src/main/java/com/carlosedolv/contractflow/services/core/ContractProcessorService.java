package com.carlosedolv.contractflow.services.core;

import java.util.List;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.entities.Installment;
import com.carlosedolv.contractflow.entities.enums.PaymentType;
import com.carlosedolv.contractflow.exceptions.InvalidPaymentMethodException;
import com.carlosedolv.contractflow.factories.DateCalculatorFactory;
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

    @Autowired
	public ContractProcessorService(
            DateCalculatorFactory dateCalculatorFactory,
            FeeCalculator feeCalculator
    ) {
        this.dateCalculatorFactory = dateCalculatorFactory;
        this.feeCalculator = feeCalculator;
    }

    @Transactional
	public Contract processContract(Contract contract) {
        validatePaymentRules(contract);
        InstallmentDateCalculator dateCalculator = dateCalculatorFactory.getCalculator(contract.getPaymentType());
        OnlinePaymentService paymentService = new PicPayPaymentService(dateCalculator, feeCalculator);
        List<Installment> installments = paymentService.processInstallments(contract);
        contract.setInstallments(installments);
        return contract;
	}

    private void validatePaymentRules(Contract contract) {
        if(contract.getPaymentType().equals(PaymentType.PIX)) {
            if(contract.getInstallmentQuantity() != 1) throw new InvalidPaymentMethodException(
                    "Contract " + contract.getNumber() + ": Invalid installment quantity for pix method."
            );
        }
    }
}
