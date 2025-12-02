package com.carlosedolv.contractflow.services;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.entities.Installment;
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
    private final ContractRepository repository;
    private final ContractProcessorService processorService;

    @Autowired
    public ContractService(ContractRepository repository, ContractProcessorService processorService) {
        this.repository = repository;
        this.processorService = processorService;
    }

    public Contract findById(Long id) {
        Optional<Contract> contract = repository.findById(id);
        return contract.orElseThrow(() -> new ResourceNotFoundException("Contract not found."));
    }

    public List<Contract> findAll() {
        return repository.findAll();
    }

    public Contract insert(Contract contract) {
        Contract processedContract = processorService.processContract(contract);
        return repository.save(processedContract);
    }

    public Contract update(Long id, Contract contract) {
        try {
            Contract reference = repository.getReferenceById(id);
            Contract processedContract = processorService.processContract(contract);
            updateReference(reference, processedContract);
            return repository.save(reference);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Contract not found.");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Contract not found.");
        }
    }

    private void updateReference(Contract reference, Contract contract) {
        reference.setNumber(contract.getNumber());
        reference.setDate(contract.getDate());
        reference.setPaymentBase(contract.getPaymentBase());
        reference.setPaymentAmount(contract.getPaymentAmount());
        reference.setInstallmentQuantity(contract.getInstallmentQuantity());
        reference.setPaymentType(contract.getPaymentType());
        reference.getInstallments().clear();
        for(Installment installment : contract.getInstallments()) {
            installment.setContract(reference);
            reference.getInstallments().add(installment);
        }
    }
}
