package com.carlosedolv.contractflow.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class DateFormatter {
    private DateFormatter() {
        throw new UnsupportedOperationException("Utility class");
    }
    
	public static LocalDate parseDate(String date) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, fmt);
	}
	
	public static String parseString(LocalDate date) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(fmt);
	}
}
