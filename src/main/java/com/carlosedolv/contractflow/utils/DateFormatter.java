package com.carlosedolv.contractflow.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class DateFormatter {
    private DateFormatter() {
        throw new UnsupportedOperationException("Utility class");
    }
    
	public static Instant parseDate(String date) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return Instant.from(fmt.parse(date));
	}
	
	public static String parseString(Instant date) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return fmt.format(date);
	}
}
