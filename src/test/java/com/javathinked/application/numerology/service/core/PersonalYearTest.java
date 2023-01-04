package com.javathinked.application.numerology.service.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PersonalYearTest extends BaseNumerologyComputerTest {

    private final PersonalYear personalYear;

    @Autowired
    PersonalYearTest(PersonalYear personalYear) {
        this.personalYear = personalYear;
    }

    @Test
    void givenPerson_whenComputeThePersonalYear_thenReturnsTheNumber() {
        var number = personalYear.computeNumberFor(person);
        assertThat(number).isEqualTo(1);
    }
}