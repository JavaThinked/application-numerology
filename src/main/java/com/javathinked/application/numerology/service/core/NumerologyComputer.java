package com.javathinked.application.numerology.service.core;

import com.javathinked.application.numerology.service.model.Person;

import java.util.stream.IntStream;

public interface NumerologyComputer {

    int computeNumberFor(Person... person);

    default int computeSumOfDigits(int number) {
        while (true) {
            int finalNumber = number;
            int sum = IntStream.range(0, String.valueOf(number).length())
                    .map(i -> java.lang.Character.getNumericValue(String.valueOf(finalNumber).charAt(i)))
                    .sum();
            if (sum >= NumerologyValue.Numbers.ONE.getValue()
                    && sum <= NumerologyValue.Numbers.NINE.getValue()
                    || sum == NumerologyValue.Numbers.ELEVEN.getValue()
                    || sum == NumerologyValue.Numbers.TWENTY_TWO.getValue()) {
                return sum;
            }
            number = sum;
        }
    }
}
