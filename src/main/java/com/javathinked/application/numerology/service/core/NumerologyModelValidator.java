package com.javathinked.application.numerology.service.core;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class NumerologyModelValidator<M> {

    private ValidatorFactory factory;
    private Validator validator;

    public Set<ConstraintViolation<M>> getConstrainsViolations(M model) {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        return validator.validate(model);
    }
}
