package com.javathinked.application.numerology.controller.exception;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable exception) {
        super(message, exception);
    }

}
