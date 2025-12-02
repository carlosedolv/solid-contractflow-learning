package com.carlosedolv.contractflow.services;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.exceptions.ResourceNotFoundException;
import com.carlosedolv.contractflow.repositories.ContractRepository;
import com.carlosedolv.contractflow.services.core.ContractProcessorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ContractProcessorService contractProcessorService;

    public Contract findById(Long id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.orElseThrow(() -> new ResourceNotFoundException("Contract not found."));
    }

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    public Contract insert(Contract contract) {
        Contract processedContract = contractProcessorService.processContract(contract);
        return contractRepository.save(processedContract);
    }

    public Contract update(Long id, Contract contract) {
        Contract reference = findById(id);
        updateReference(reference, contract);
        return contractRepository.save(contract);
    }

    public void delete(Long id) {
        try {
            contractRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Contract not found.");
        }
    }

    private void updateReference(Contract reference, Contract contract) {
        reference.setNumber(contract.getNumber());
        reference.setDate(contract.getDate());
        reference.setTotal(contract.getTotal());
        reference.setInstallmentQuantity(contract.getInstallmentQuantity());
    }
}
