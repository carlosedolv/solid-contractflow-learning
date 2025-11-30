package com.carlosedolv.contractflow.ui;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Scanner;

import com.carlosedolv.contractflow.enums.PaymentType;
import com.carlosedolv.contractflow.exceptions.InvalidDateException;
import com.carlosedolv.contractflow.exceptions.InvalidBigDecimalException;
import com.carlosedolv.contractflow.exceptions.InvalidIntegerException;
import com.carlosedolv.contractflow.utils.InputReader;

public class UserInputHandler {
	private final Scanner scanner;

	public UserInputHandler(Scanner sc) {
		scanner = sc;
	}

	public int readIntWithRetry(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				return InputReader.readPositiveInteger(scanner);
			} catch (InvalidIntegerException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	public BigDecimal readBigDecimalWithRetry(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				return InputReader.readPositiveBigDecimal(scanner);
			} catch (InvalidBigDecimalException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	public LocalDate readDateWithRetry(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				return InputReader.readDate(scanner);
			} catch (InvalidDateException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

    public PaymentType readPaymentTypeWithRetry(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return PaymentType.fromCode(scanner.next());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
