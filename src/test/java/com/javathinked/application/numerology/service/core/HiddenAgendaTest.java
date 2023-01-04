package com.javathinked.application.numerology.service.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class HiddenAgendaTest extends BaseNumerologyComputerTest {

    @Autowired
    private HiddenAgenda hiddenAgenda;

    @Test
    void givenPerson_whenComputeTheHiddenAgenda_thenReturnsTheNumber() {
        var number = hiddenAgenda.computeNumberFor(person);
        assertThat(number).isEqualTo(7);
    }
}