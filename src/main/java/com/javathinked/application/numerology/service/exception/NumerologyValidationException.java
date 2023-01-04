package com.javathinked.application.numerology.service.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class NumerologyValidationException extends ConstraintViolationException {

    public NumerologyValidationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
