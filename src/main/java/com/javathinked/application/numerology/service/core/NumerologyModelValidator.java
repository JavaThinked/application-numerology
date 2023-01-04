package com.javathinked.application.numerology.service.core;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

@Component
public class NumerologyModelValidator<M> {

    public Set<ConstraintViolation<M>> getConstrainsViolations(M model) {
        var factory = Validation.buildDefaultValidatorFactory();
        var validator = factory.getValidator();
        return validator.validate(model);
    }
}
