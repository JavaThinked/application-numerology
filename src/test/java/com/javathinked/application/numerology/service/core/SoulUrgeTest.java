package com.javathinked.application.numerology.service.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SoulUrgeTest extends BaseNumerologyComputerTest {

    private final SoulUrge soulUrge;

    @Autowired
    SoulUrgeTest(SoulUrge soulUrge) {
        this.soulUrge = soulUrge;
    }

    @Test
    void givenPerson_whenComputeTheSoulUrge_thenReturnsTheNumber() {
        var number = soulUrge.computeNumberFor(person);
        assertThat(number).isEqualTo(6);
    }
}