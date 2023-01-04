package com.javathinked.application.numerology.service.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LoveCompatibilityTest extends BaseNumerologyComputerTest {

    @Autowired
    private LoveCompatibility loveCompatibility;

    @Test
    void givenPerson_whenComputeTheLoveCompatibility_thenReturnsTheNumber() {
        var number = loveCompatibility.computeNumberFor(person, person);
        assertThat(number).isEqualTo(8);
    }
}