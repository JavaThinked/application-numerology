package com.javathinked.application.numerology.service.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DivinePurposeTest extends BaseNumerologyComputerTest {

    @Autowired
    private DivinePurpose divinePurpose;

    @Test
    void givenPerson_whenComputeTheDivinePurpose_thenReturnsTheNumber() {
        var number = divinePurpose.computeNumberFor(person);
        assertThat(number).isEqualTo(6);
    }
}