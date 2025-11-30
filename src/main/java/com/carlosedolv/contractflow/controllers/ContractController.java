package com.carlosedolv.contractflow.controllers;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @GetMapping
    public ResponseEntity<List<Contract>> getContracts() {
        List<Contract> contracts = contractService.findAll();
        return ResponseEntity.ok().body(contracts);
    }
}
