package com.carlosedolv.contractflow.exceptions;

import java.io.Serial;

public class InvalidBigDecimalException extends RuntimeException {
    @Serial
	private static final long serialVersionUID = 1L;
	
	public InvalidBigDecimalException(String msg) {
		super(msg);
	}
}
