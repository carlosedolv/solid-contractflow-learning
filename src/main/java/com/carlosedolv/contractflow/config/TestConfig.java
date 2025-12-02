package com.carlosedolv.contractflow.config;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.carlosedolv.contractflow.entities.enums.PaymentType;
import com.carlosedolv.contractflow.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.carlosedolv.contractflow.entities.Contract;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private ContractService service;

	@Override
	public void run(String... args) throws Exception {
        System.out.println("=== Populating Database with Sample Data ===");

        createContract(1001, LocalDate.now(),
                new BigDecimal("12400.00"), 24,
                PaymentType.DEBIT
        );

        createContract(1001, LocalDate.now(),
                new BigDecimal("500.00"), 3,
                PaymentType.DEBIT
        );

        createContract(1002, LocalDate.now(),
                new BigDecimal("2400.00"), 12,
                PaymentType.CREDIT
        );

        createContract(1003, LocalDate.now(),
                new BigDecimal("6732.00"), 1,
                PaymentType.PIX
        );

        System.out.println("=== Database populated successfully! ===");
    }

    private void createContract(
            int number,
            LocalDate date,
            BigDecimal total,
            int installmentQuantity,
            PaymentType paymentType
    ) {
        try {
            Contract contract = new Contract(
                    null,
                    number,
                    date,
                    new BigDecimal(String.valueOf(total)),
                    installmentQuantity,
                    paymentType
            );

            Contract saved = service.insert(contract);
            System.out.println("✅ Contract " + number + " created (ID: " + saved.getId() + ")");

        } catch (Exception e) {
            System.err.println("Failed to create contract " + number + ": " + e.getMessage());
        }
    }
	
}
