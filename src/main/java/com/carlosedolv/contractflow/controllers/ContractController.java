package com.carlosedolv.contractflow.controllers;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService service;

    @Autowired
    public ContractController(ContractService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Contract>> findAll() {
        List<Contract> contracts = service.findAll();
        return ResponseEntity.ok().body(contracts);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Contract> findById(@PathVariable Long id) {
        Contract obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Contract> insert(@RequestBody Contract contract) {
        Contract obj = service.insert(contract);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Contract> update(@PathVariable Long id, @RequestBody Contract contract) {
        Contract obj = service.update(id, contract);
        return ResponseEntity.ok().body(obj);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Contract> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
