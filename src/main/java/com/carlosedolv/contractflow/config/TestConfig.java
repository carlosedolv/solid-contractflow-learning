package com.carlosedolv.contractflow.config;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import com.carlosedolv.contractflow.enums.PaymentType;
import com.carlosedolv.contractflow.repositories.ContractRepository;
import com.carlosedolv.contractflow.repositories.InstallmentRepository;
import com.carlosedolv.contractflow.services.ContractService;
import com.carlosedolv.contractflow.services.core.ContractProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.entities.Installment;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    ContractProcessorService processorService;

    @Autowired
    ContractService contractService;

	@Override
	public void run(String... args) throws Exception {
// ✅ Contrato 1: Crédito
        Contract c1 = new Contract(
                null, 1001, LocalDate.now(),
                new BigDecimal("1200.00"), 12,
                PaymentType.CREDIT
        );
        contractService.insert(processorService.processContract(c1));

        // ✅ Contrato 2: Débito + Juros Compostos
        Contract c2 = new Contract(
                null, 1002, LocalDate.now(),
                new BigDecimal("2400.00"), 6,
                PaymentType.DEBIT
        );
        contractService.insert(processorService.processContract(c2));

        // ✅ Contrato 3: Boleto + Tabela Price
        Contract c3 = new Contract(
                null, 1003, LocalDate.now(),
                new BigDecimal("5000.00"), 10,
                PaymentType.PIX
        );
        contractService.insert(processorService.processContract(c3));

	}
	
}
