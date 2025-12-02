package com.carlosedolv.contractflow.services.core;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.entities.Installment;
import com.carlosedolv.contractflow.entities.enums.PaymentType;
import com.carlosedolv.contractflow.exceptions.InvalidPaymentMethodException;
import com.carlosedolv.contractflow.services.contracts.OnlinePaymentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ContractProcessorService {
    private final OnlinePaymentService onlinePaymentService;

	public ContractProcessorService(OnlinePaymentService paymentService) {
        this.onlinePaymentService = paymentService;
    }

    @Transactional
	public Contract processContract(Contract contract) {
        validatePaymentRules(contract);
        List<Installment> installments = onlinePaymentService.processInstallments(contract);
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
