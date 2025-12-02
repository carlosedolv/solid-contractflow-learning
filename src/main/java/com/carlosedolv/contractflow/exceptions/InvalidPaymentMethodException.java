package com.carlosedolv.contractflow.exceptions;

import java.io.Serial;


public class InvalidPaymentMethodException extends RuntimeException {
    @Serial
	private static final long serialVersionUID = 1L;

	public InvalidPaymentMethodException(String msg) {
		super(msg);
	}
}
