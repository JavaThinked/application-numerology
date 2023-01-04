package com.javathinked.application.numerology.service.core;

import com.javathinked.application.numerology.service.model.Person;
import org.springframework.stereotype.Service;

@Service("Attitude")
public class Attitude implements NumerologyComputer {

    @Override
    public int computeNumberFor(Person... person) {
        var attitudeToCompute = person[0].getDay() + person[0].getMonth();
        return computeSumOfDigits(attitudeToCompute);
    }
}
