package com.carlosedolv.contractflow.controllers;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.services.ContractService;
import com.carlosedolv.contractflow.services.core.ContractProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    private ContractProcessorService processorService;
    @Autowired
    private ContractService contractService;

    @GetMapping
    public ResponseEntity<List<Contract>> getContracts() {
        List<Contract> contracts = contractService.findAll();
        return ResponseEntity.ok().body(contracts);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
        Contract processedContract = processorService.processContract(contract);
        return ResponseEntity.ok().body(processedContract);
    }
}
