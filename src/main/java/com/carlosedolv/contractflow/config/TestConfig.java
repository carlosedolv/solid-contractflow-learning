package com.carlosedolv.contractflow.config;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.carlosedolv.contractflow.entities.Contract;
import com.carlosedolv.contractflow.entities.Installment;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		Contract c1 = new Contract(null, 1, Instant.now(), 1200.00, 5);
		Installment i1 = new Installment(null, Instant.now(), 3432.00, c1);
	}
	
}
