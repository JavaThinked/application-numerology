package com.javathinked.application.numerology.service.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AttitudeTest extends BaseNumerologyComputerTest {

    @Autowired
    private Attitude attitude;

    @Test
    void givenPerson_whenComputeTheAttitude_thenReturnsTheNumber() {
        var number = attitude.computeNumberFor(person);
        assertThat(number).isEqualTo(3);
    }

}