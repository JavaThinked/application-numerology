package com.javathinked.application.numerology.service.core;

import com.javathinked.application.numerology.service.model.Person;
import org.springframework.stereotype.Service;

@Service("HiddenAgenda")
public class HiddenAgenda implements NumerologyComputer {

    @Override
    public int computeNumberFor(Person... person) {
        var fullName = person[0].getFirstName().toUpperCase() + person[0].getLastName().toUpperCase();
        var hiddenAgendaToCompute = 0;
        for(int i = 0; i < fullName.length(); i++) {
            var vowelChar = fullName.charAt(i);
            if(NumerologyValue.isConsonant(String.valueOf(vowelChar))) {
                hiddenAgendaToCompute += NumerologyValue.getLetterValues().get(String.valueOf(vowelChar));
            }
        }
        return computeSumOfDigits(hiddenAgendaToCompute);
    }
}
