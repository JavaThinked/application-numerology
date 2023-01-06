package com.javathinked.application.numerology.service.core;

import com.javathinked.application.numerology.configuration.TestApplicationConfiguration;
import com.javathinked.application.numerology.data.TestData;
import com.javathinked.application.numerology.service.model.Person;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig(TestApplicationConfiguration.class)
abstract class BaseNumerologyComputerTest implements WithAssertions {

    protected Person person;

    @BeforeEach
    void setUp() {
        person = TestData.createPerson();
    }
}