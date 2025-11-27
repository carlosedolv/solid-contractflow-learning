package com.carlosedolv.contractflow.exceptions;

import java.io.Serial;

public class InvalidDoubleException extends RuntimeException {
    @Serial
	private static final long serialVersionUID = 1L;
	
	public InvalidDoubleException(String msg) {
		super(msg);
	}
}
