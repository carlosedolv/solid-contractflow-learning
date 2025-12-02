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
    private ContractService service;

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

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Contract> update(@PathVariable Long id, @RequestBody Contract contract) {
        Contract obj = service.update(id, contract);
        return ResponseEntity.ok().body(obj);

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Contract> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
