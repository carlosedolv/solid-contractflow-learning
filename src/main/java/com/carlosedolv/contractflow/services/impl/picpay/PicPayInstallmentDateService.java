package com.carlosedolv.contractflow.services.impl.picpay;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.carlosedolv.contractflow.services.contracts.InstallmentDateService;

public class PicPayInstallmentDateService implements InstallmentDateService{

	@Override
	public Instant calculate(Instant start, int installmentNumber) {
		ZonedDateTime zdt = start.atZone(ZoneId.of("America/Sao_Paulo"));
		ZonedDateTime zdtPlusMonth = zdt.plusMonths(installmentNumber);
		return zdtPlusMonth.toInstant();
	}
}
