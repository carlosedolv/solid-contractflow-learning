package com.carlosedolv.contractflow.ui;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.carlosedolv.contractflow.entities.Contract;
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
			Instant date = inputHandler.readDateWithRetry("Start (dd/MM/yyyy hh:mm): ");
			Double base = inputHandler.readDoubleWithRetry("Contract price: ");
			int installmentQuantity = inputHandler.readIntWithRetry("How many installments would you like: ");
			contracts.add(new Contract(null, number, date, base, installmentQuantity));
		}

		return contracts;
	}

	public void printContracts(List<Contract> contracts) {
		for (Contract c : contracts) {
			System.out.println("--- Contract " + c.getNumber() + " ---");
			System.out.println("Date: " + DateFormatter.parseInstant(c.getDate()));
			System.out.println("Total: " + c.getTotal());
			System.out.println("Installments:");

			c.getInstallments().forEach(inst -> {
				System.out.println(DateFormatter.parseInstant(inst.getDate()) + " - " + inst.getPrice());
			});
		}
	}
}
