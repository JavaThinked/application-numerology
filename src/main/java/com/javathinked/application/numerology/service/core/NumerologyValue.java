package com.javathinked.application.numerology.service.core;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Numbers.*;

@UtilityClass
public class NumerologyValue {

    public enum Category {

        DESTINY("Destiny", "destiny", "DESTINY"),
        PERSONALITY("Personality", "personality", "PERSONALITY"),
        ATTITUDE("Attitude", "attitude", "ATTITUDE"),
        CHARACTER("Character", "character", "CHARACTER"),
        SOUL_URGE("SoulUrge", "soul_urge", "SOUL URGE"),
        HIDDEN_AGENDA("HiddenAgenda", "hidden_agenda", "HIDDEN AGENDA"),
        DIVINE_PURPOSE("DivinePurpose", "divine_purpose", "DIVINE PURPOSE"),
        PERSONAL_YEAR("PersonalYear", "personal_year", "PERSONAL YEAR"),
        LOVE_COMPATIBILITY("LoveCompatibility", "love_compatibility", "LOVE COMPATIBILITY"),
        FINAL_RESULT("FinalResult", "final_result", "FINAL RESULT");

        private final String name;
        private final String description;

        private final String beanName;

        Category(String beanName, String name, String description) {
            this.beanName = beanName;
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return this.name;
        }

        public String getDescription() {
            return this.description;
        }

        public String getBeanName() {
            return beanName;
        }
    }

    public enum Language {
        ENGLISH("en"),
        FRENCH("fr");

        private final String value;

        Language(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Numbers {

        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        ELEVEN(11),
        TWENTY_TWO(22);

        private final int value;

        Numbers(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private final List<Integer> validNumbers = Arrays.asList(ONE.getValue(), TWO.getValue(), THREE.getValue(),
            FOUR.getValue(), FIVE.getValue(), SIX.getValue(),
            SEVEN.getValue(), EIGHT.getValue(), NINE.getValue(),
            ELEVEN.getValue(), TWENTY_TWO.getValue());

    private final Map<String, Integer> letterValues = new HashMap<>();

    static {
        letterValues.put("A", 1);
        letterValues.put("B", 2);
        letterValues.put("C", 3);
        letterValues.put("D", 4);
        letterValues.put("E", 5);
        letterValues.put("F", 6);
        letterValues.put("G", 7);
        letterValues.put("H", 8);
        letterValues.put("I", 9);
        letterValues.put("J", 1);
        letterValues.put("K", 2);
        letterValues.put("L", 3);
        letterValues.put("M", 4);
        letterValues.put("N", 5);
        letterValues.put("O", 6);
        letterValues.put("P", 7);
        letterValues.put("Q", 8);
        letterValues.put("R", 9);
        letterValues.put("S", 1);
        letterValues.put("T", 2);
        letterValues.put("U", 3);
        letterValues.put("V", 4);
        letterValues.put("W", 5);
        letterValues.put("X", 6);
        letterValues.put("Y", 7);
        letterValues.put("Z", 8);
    }

    public Map<String, Integer> getLetterValues() {
        return letterValues;
    }

    public static List<Integer> getValidNumbers() {
        return validNumbers;
    }

    private final List<String> vowels = Arrays.asList("A", "E", "I", "O", "U", "Y");
    private final List<String> consonants = Arrays.asList("B", "C", "D", "F", "G",
            "H", "J", "K", "L", "M",
            "N", "P", "Q", "R", "S",
            "T", "V", "W", "X", "Z");

    public boolean isVowel(String letter) {
        return vowels.contains(letter);
    }

    public boolean isConsonant(String letter) {
        return consonants.contains(letter);
    }
}
