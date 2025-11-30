package com.carlosedolv.contractflow.services;

import com.carlosedolv.contractflow.entities.Installment;
import com.carlosedolv.contractflow.repositories.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstallmentService {
    @Autowired
    private InstallmentRepository repository;

    public Installment findById(Long id) {
        Optional<Installment> installment = repository.findById(id);
        return installment.orElseThrow(() -> new RuntimeException("Installment not found"));
    }

    public List<Installment> findAll() {
        return repository.findAll();
    }

    public Installment insert(Installment installment) {
        return repository.save(installment);
    }

    public Installment update(Long id, Installment installment) {
        try {
            Installment reference = findById(id);
            updateReference(reference, installment);
            return repository.save(reference);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateReference(Installment reference, Installment installment) {
        reference.setId(installment.getId());
        reference.setDate(installment.getDate());
        reference.setPrice(installment.getPrice());
    }

}
