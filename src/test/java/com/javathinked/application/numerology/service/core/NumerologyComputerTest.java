package com.javathinked.application.numerology.service.core;

import com.javathinked.application.numerology.configuration.TestApplicationConfig;
import com.javathinked.application.numerology.data.TestData;
import com.javathinked.application.numerology.service.model.Person;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Numbers.*;


@SpringJUnitWebConfig(TestApplicationConfig.class)
class NumerologyComputerTest implements WithAssertions {

    private final Destiny sampleComputer;
    protected Person person;

    @Autowired
    NumerologyComputerTest(Destiny sampleComputer) {
        this.sampleComputer = sampleComputer;
    }

    @BeforeEach
    void setUp() {
        person = TestData.createPerson();
    }

    @Test
    void givenNumber_whenComputeSumOfDigit_thenReturnOne() {
        assertThat(sampleComputer.computeSumOfDigits(1000)).isEqualTo(ONE.getValue());
    }

    @Test
    void givenNumber_whenComputeSumOfDigit_thenReturnTwo() {
        assertThat(sampleComputer.computeSumOfDigits(1001)).isEqualTo(TWO.getValue());
    }

    @Test
    void givenNumber_whenComputeSumOfDigit_thenReturnThree() {
        assertThat(sampleComputer.computeSumOfDigits(1101)).isEqualTo(THREE.getValue());
    }

    @Test
    void givenNumber_whenComputeSumOfDigit_thenReturnFour() {
        assertThat(sampleComputer.computeSumOfDigits(1201)).isEqualTo(FOUR.getValue());
    }

    @Test
    void givenNumber_whenComputeSumOfDigit_thenReturnFive() {
        assertThat(sampleComputer.computeSumOfDigits(3011)).isEqualTo(FIVE.getValue());
    }

    @Test
    void givenNumber_whenComputeSumOfDigit_thenReturnSix() {
        assertThat(sampleComputer.computeSumOfDigits(1131)).isEqualTo(SIX.getValue());
    }

    @Test
    void givenNumber_whenComputeSumOfDigit_thenReturnSeven() {
        assertThat(sampleComputer.computeSumOfDigits(2041)).isEqualTo(SEVEN.getValue());
    }

    @Test
    void givenNumber_whenComputeSumOfDigit_thenReturnEight() {
        assertThat(sampleComputer.computeSumOfDigits(3203)).isEqualTo(EIGHT.getValue());
    }

    @Test
    void givenNumber_whenComputeSumOfDigit_thenReturnNine() {
        assertThat(sampleComputer.computeSumOfDigits(1701)).isEqualTo(NINE.getValue());
    }

    @Test
    void givenNumber_whenComputeSumOfDigit_thenReturnEleven() {
        assertThat(sampleComputer.computeSumOfDigits(1082)).isEqualTo(ELEVEN.getValue());
    }

    @Test
    void givenNumber_whenComputeSumOfDigit_thenReturnTwentyTwo() {
        assertThat(sampleComputer.computeSumOfDigits(8383)).isEqualTo(TWENTY_TWO.getValue());
    }
}