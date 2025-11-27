package com.carlosedolv.contractflow.exceptions;

import java.io.Serial;

public class InvalidIntegerException extends RuntimeException {
    @Serial
	private static final long serialVersionUID = 1L;
	
	public InvalidIntegerException(String msg) {
		super(msg);
	}
}
