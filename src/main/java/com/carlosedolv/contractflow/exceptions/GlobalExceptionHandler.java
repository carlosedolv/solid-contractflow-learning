package com.carlosedolv.contractflow.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidPaymentMethodException.class)
    public ResponseEntity<StandardError> handlePaymentMethod(InvalidPaymentMethodException e, HttpServletRequest request) {
        StandardError standardError = new StandardError(
                Instant.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(standardError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError standardError = new StandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }
}
