package com.carlosedolv.contractflow.repositories;

import com.carlosedolv.contractflow.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
