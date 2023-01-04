package com.javathinked.application.numerology.service.core;

import com.javathinked.application.numerology.service.model.Person;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("PersonalYear")
public class PersonalYear implements NumerologyComputer {

    @Override
    public int computeNumberFor(Person... person) {
        var personalYearToCompute = person[0].getMonth() + person[0].getDay() + LocalDate.now().getYear();
        return computeSumOfDigits(personalYearToCompute);
    }
}
