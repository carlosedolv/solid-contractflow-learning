package com.carlosedolv.contractflow.ui;

import java.time.Instant;
import java.util.Scanner;

import com.carlosedolv.contractflow.exceptions.InvalidDateException;
import com.carlosedolv.contractflow.exceptions.InvalidDoubleException;
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

	public Double readDoubleWithRetry(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				return InputReader.readPositiveDouble(scanner);
			} catch (InvalidDoubleException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	public Instant readDateWithRetry(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				return InputReader.readDate(scanner);
			} catch (InvalidDateException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
