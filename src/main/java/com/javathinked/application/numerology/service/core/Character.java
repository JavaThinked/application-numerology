package com.javathinked.application.numerology.service.core;

import com.javathinked.application.numerology.service.model.Person;
import org.springframework.stereotype.Service;

@Service("Character")
public class Character implements NumerologyComputer {

    @Override
    public int computeNumberFor(Person... person) {
        var fullName = person[0].getFirstName().toUpperCase() + person[0].getLastName().toUpperCase();
        var characterToCompute = 0;
        for(int i = 0; i < fullName.length(); i++) {
            characterToCompute += NumerologyValue.getLetterValues().get(String.valueOf(fullName.charAt(i)));
        }
        return computeSumOfDigits(characterToCompute);
    }
}
