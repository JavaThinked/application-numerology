package com.javathinked.application.numerology.service.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PersonalityTest extends BaseNumerologyComputerTest {

    @Autowired
    private Personality personality;

    @Test
    void givenPerson_whenComputeThePersonality_thenReturnsTheNumber() {
        var number = personality.computeNumberFor(person);
        assertThat(number).isEqualTo(3);
    }
}