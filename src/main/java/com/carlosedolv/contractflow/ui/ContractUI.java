package com.carlosedolv.contractflow.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.enums.PaymentType;
import com.carlosedolv.contractflow.utils.DateFormatter;

public class ContractUI {
	private final UserInputHandler inputHandler;

	public ContractUI(Scanner sc) {
		this.inputHandler = new UserInputHandler(sc);
	}

	public List<Contract> readContracts() {
		List<Contract> contracts = new ArrayList<>();
		int count = inputHandler.readIntWithRetry("How many contracts do you want to rent: ");

		for (int i = 0; i < count; i++) {
			int number = inputHandler.readIntWithRetry("Contract number: ");
			LocalDate date = inputHandler.readDateWithRetry("Start (dd/MM/yyyy): ");
			BigDecimal base = inputHandler.readBigDecimalWithRetry("Contract price: ");
            PaymentType paymentMethod = inputHandler.readPaymentTypeWithRetry("Payment type (debit, credit, pix): ");

            if(paymentMethod == PaymentType.PIX) {
                contracts.add(new Contract(null, number, date, base, 1, paymentMethod));
            } else {
                int installmentQuantity = inputHandler.readIntWithRetry("Installment quantity: ");
                contracts.add(new Contract(null, number, date, base, installmentQuantity, paymentMethod));
            }
		}

		return contracts;
	}

	public void printContracts(List<Contract> contracts) {
		for (Contract c : contracts) {
			System.out.println("--- Contract " + c.getNumber() + " ---");
			System.out.println("Date: " + DateFormatter.parseString(c.getDate()));
			System.out.println("Total: $" + c.getTotal());
            System.out.println("Payment Type: " +  c.getPaymentType().getValue());

			System.out.println("Installments:");
			c.getInstallments().forEach(inst -> {
                BigDecimal price = inst.getPrice().setScale(2, RoundingMode.HALF_UP);
				System.out.println(DateFormatter.parseString(inst.getDate()) + " - $" + price);
			});
		}
	}
}
