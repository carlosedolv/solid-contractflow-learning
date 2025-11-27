package com.carlosedolv.contractflow.services.contracts;

import java.util.List;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.entities.Installment;

public interface OnlinePaymentService {
	List<Installment> processInstallments(Contract contract);
}
