package com.javathinked.application.numerology.service.core;

import com.javathinked.application.numerology.service.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DivinePurpose")
public class DivinePurpose implements NumerologyComputer {

    private Destiny destiny;
    private Character character;

    @Autowired
    public DivinePurpose(Destiny destiny, Character character) {
        this.destiny = destiny;
        this.character = character;
    }

    @Override
    public int computeNumberFor(Person... person) {
        var divinePurpose = destiny.computeNumberFor(person) + character.computeNumberFor(person);
        return computeSumOfDigits(divinePurpose);
    }
}
