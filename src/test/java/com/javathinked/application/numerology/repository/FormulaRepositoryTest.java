package com.javathinked.application.numerology.repository;

import com.javathinked.application.numerology.service.core.NumerologyValue;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Category.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class FormulaRepositoryTest implements WithAssertions {

    private final FormulaRepository repository;

    @Autowired
    FormulaRepositoryTest(FormulaRepository repository) {
        this.repository = repository;
    }

    @Test
    void givenRepository_whenGetFormulas_thenReturnAll() {
        var formulas = repository.findAll();
        assertAll(
                () -> assertThat(formulas).isNotEmpty(),
                () -> assertThat(formulas).anyMatch(formula -> DESTINY.getName().equals(formula.getCategory())),
                () -> assertThat(formulas).anyMatch(formula -> PERSONALITY.getName().equals(formula.getCategory())),
                () -> assertThat(formulas).anyMatch(formula -> ATTITUDE.getName().equals(formula.getCategory())),
                () -> assertThat(formulas).anyMatch(formula -> NumerologyValue.Category.CHARACTER.getName().equals(formula.getCategory())),
                () -> assertThat(formulas).anyMatch(formula -> SOUL_URGE.getName().equals(formula.getCategory())),
                () -> assertThat(formulas).anyMatch(formula -> HIDDEN_AGENDA.getName().equals(formula.getCategory())),
                () -> assertThat(formulas).anyMatch(formula -> DIVINE_PURPOSE.getName().equals(formula.getCategory())),
                () -> assertThat(formulas).anyMatch(formula -> PERSONAL_YEAR.getName().equals(formula.getCategory())),
                () -> assertThat(formulas).anyMatch(formula -> LOVE_COMPATIBILITY.getName().equals(formula.getCategory()))
        );
    }

}