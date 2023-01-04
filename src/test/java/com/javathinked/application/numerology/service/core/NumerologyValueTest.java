package com.javathinked.application.numerology.service.core;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class NumerologyValueTest implements WithAssertions {

    private final static int ALPHABET_TOTAL_LETTERS = 26;

    @Test
    void givenLetters_whenGetLetters_thenReturns26() {
        assertThat(NumerologyValue.getLetterValues().size()).isEqualTo(ALPHABET_TOTAL_LETTERS);
    }

    @Test
    void givenLetters_whenLetterIsNotVowel_thenReturnsFalse() {
        assertThat(NumerologyValue.isVowel("J")).isFalse();
    }

    @Test
    void givenLetters_whenLetterIsVowel_thenReturnsTrue() {
        assertThat(NumerologyValue.isVowel("A")).isTrue();
    }

    @Test
    void givenLetters_whenLetterIsConsonant_thenReturnsTrue() {
        assertThat(NumerologyValue.isConsonant("V")).isTrue();
    }

    @Test
    void givenLetters_whenLetterIsNotConsonant_thenReturnsFalse() {
        assertThat(NumerologyValue.isConsonant("A")).isFalse();
    }
}