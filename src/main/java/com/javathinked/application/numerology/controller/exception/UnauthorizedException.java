package com.javathinked.application.numerology.controller.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class UnauthorizedException extends BadCredentialsException {

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
