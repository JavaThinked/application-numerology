package com.javathinked.application.numerology.service;

import com.javathinked.application.numerology.data.TestData;
import com.javathinked.application.numerology.repository.FormulaRepository;
import com.javathinked.application.numerology.service.core.NumerologyValue;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Category.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FormulaServiceTest implements WithAssertions {

    @Mock
    private FormulaRepository repository;

    @InjectMocks
    private FormulaService underTest;

    @Test
    void givenFormula_whenGetFormulaForDestiny_thenReturnExpression() {
        when(repository.findByCategory(anyString())).thenReturn(TestData.formulaOf(DESTINY));
        assertThat(underTest.findFormulaByCategory(DESTINY).getExpression()).contains("Month + Day + Year");
    }

    @Test
    void givenFormula_whenGetFormulaForPersonality_thenReturnExpression() {
        when(repository.findByCategory(anyString())).thenReturn(TestData.formulaOf(PERSONALITY));
        assertThat(underTest.findFormulaByCategory(PERSONALITY).getExpression()).contains("Day");
    }

    @Test
    void givenFormula_whenGetFormulaForAttitude_thenReturnExpression() {
        when(repository.findByCategory(anyString())).thenReturn(TestData.formulaOf(ATTITUDE));
        assertThat(underTest.findFormulaByCategory(ATTITUDE).getExpression()).contains("Month + Day");
    }

    @Test
    void givenFormula_whenGetFormulaForCharacter_thenReturnExpression() {
        when(repository.findByCategory(anyString())).thenReturn(TestData.formulaOf(NumerologyValue.Category.CHARACTER));
        assertThat(underTest.findFormulaByCategory(NumerologyValue.Category.CHARACTER).getExpression()).contains("Sum of Letters");
    }

    @Test
    void givenFormula_whenGetFormulaForSoulUrge_thenReturnExpression() {
        when(repository.findByCategory(anyString())).thenReturn(TestData.formulaOf(SOUL_URGE));
        assertThat(underTest.findFormulaByCategory(SOUL_URGE).getExpression()).contains("Sum of Vowels");
    }

    @Test
    void givenFormula_whenGetFormulaForHiddenAgenda_thenReturnExpression() {
        when(repository.findByCategory(anyString())).thenReturn(TestData.formulaOf(HIDDEN_AGENDA));
        assertThat(underTest.findFormulaByCategory(HIDDEN_AGENDA).getExpression()).contains("Sum of Consonants");
    }

    @Test
    void givenFormula_whenGetFormulaForDivinePurpose_thenReturnExpression() {
        when(repository.findByCategory(anyString())).thenReturn(TestData.formulaOf(DIVINE_PURPOSE));
        assertThat(underTest.findFormulaByCategory(DIVINE_PURPOSE).getExpression()).contains("Destiny + Character");
    }

    @Test
    void givenFormula_whenGetFormulaForPersonalYear_thenReturnExpression() {
        when(repository.findByCategory(anyString())).thenReturn(TestData.formulaOf(PERSONAL_YEAR));
        assertThat(underTest.findFormulaByCategory(PERSONAL_YEAR).getExpression()).contains("Month + Day + Current Year");
    }

    @Test
    void givenFormula_whenGetFormulaForLoveCompatibility_thenReturnExpression() {
        when(repository.findByCategory(anyString())).thenReturn(TestData.formulaOf(LOVE_COMPATIBILITY));
        assertThat(underTest.findFormulaByCategory(LOVE_COMPATIBILITY).getExpression()).contains("Sum last names");
    }
}
