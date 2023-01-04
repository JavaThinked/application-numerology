package com.javathinked.application.numerology.service.core;

import com.javathinked.application.numerology.service.model.Person;
import org.springframework.stereotype.Service;

@Service("Destiny")
public class Destiny implements NumerologyComputer {

    @Override
    public int computeNumberFor(Person... person) {
        var destinyToCompute = person[0].getMonth() + person[0].getDay() + person[0].getYear();
        return computeSumOfDigits(destinyToCompute);
    }
}
