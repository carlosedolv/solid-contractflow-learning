package com.carlosedolv.contractflow.repositories;

import com.carlosedolv.contractflow.entities.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
}
