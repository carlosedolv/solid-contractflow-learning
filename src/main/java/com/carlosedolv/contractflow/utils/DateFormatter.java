package com.carlosedolv.contractflow.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class DateFormatter {
    private DateFormatter() {
        throw new UnsupportedOperationException("Utility class");
    }
    
	public static Instant parseDate(String date) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime ldt = LocalDateTime.parse(date, fmt);
		ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Sao_Paulo"));
		return zdt.toInstant();
	}
	
	public static String parseInstant(Instant date) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		ZoneId zone = ZoneId.of("America/Sao_Paulo");
		return date.atZone(zone).format(fmt);
	}
}
