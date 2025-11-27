package com.carlosedolv.contractflow.controllers;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.services.core.ContractService;

public class ContractController {
	private final ContractService service;

	public ContractController(ContractService service) {
		this.service = service;
	}

	public Contract processContract(Contract baseContract) {
		return service.processContract(baseContract);
	}
}
