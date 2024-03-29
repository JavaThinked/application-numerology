package com.javathinked.application.numerology.service.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class DestinyTest extends BaseNumerologyComputerTest {

    private final Destiny destiny;

    @Autowired
    DestinyTest(Destiny destiny) {
        this.destiny = destiny;
    }

    @Test
    void givenPerson_whenComputeTheDestiny_thenReturnsTheNumber() {
        var number = destiny.computeNumberFor(person);
        assertThat(number).isEqualTo(11);
    }


}