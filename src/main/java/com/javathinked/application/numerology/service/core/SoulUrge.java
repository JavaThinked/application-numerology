package com.javathinked.application.numerology.service.core;

import com.javathinked.application.numerology.service.model.Person;
import org.springframework.stereotype.Service;

@Service("SoulUrge")
public class SoulUrge implements NumerologyComputer {

    @Override
    public int computeNumberFor(Person... person) {
        var fullName = person[0].getFirstName() + person[0].getLastName();
        var soulUrgeToCompute = 0;
        for(int i = 0; i < fullName.length(); i++) {
            var vowelChar = fullName.charAt(i);
            if(NumerologyValue.isVowel(String.valueOf(vowelChar))) {
                soulUrgeToCompute += NumerologyValue.getLetterValues().get(String.valueOf(vowelChar));
            }
        }
        return computeSumOfDigits(soulUrgeToCompute);
    }
}
