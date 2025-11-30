package com.carlosedolv.contractflow.services;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    public Contract findById(Long id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    public Contract insert(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract update(Long id, Contract contract) {
        try {
            Contract reference = findById(id);
            updateReference(reference, contract);
            return contractRepository.save(reference);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        try {
            contractRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateReference(Contract reference, Contract contract) {
        reference.setNumber(contract.getNumber());
        reference.setDate(contract.getDate());
        reference.setTotal(contract.getTotal());
        reference.setInstallmentQuantity(contract.getInstallmentQuantity());
    }

}
