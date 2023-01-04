package com.javathinked.application.numerology.service.exception;

public class NumerologyResultNotFoundException extends RuntimeException {

    public NumerologyResultNotFoundException() {
        super();
    }

    public NumerologyResultNotFoundException(String message) {
        super(message);
    }

    public NumerologyResultNotFoundException(String message, Throwable exception) {
        super(message, exception);
    }
}
