package com.carlosedolv.contractflow.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.ui.ContractUI;

public class AppTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        try (sc) {
            ContractUI contractUI = new ContractUI(sc);
            List<Contract> contracts = contractUI.readContracts();
            List<Contract> processedContracts = new ArrayList<>();


            contractUI.printContracts(processedContracts);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
	}
}
