package com.javathinked.application.numerology.service;

import com.javathinked.application.numerology.data.TestData;
import com.javathinked.application.numerology.repository.ResultDescriptionRepository;
import com.javathinked.application.numerology.service.core.NumerologyValue;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Category.*;
import static com.javathinked.application.numerology.service.core.NumerologyValue.Language.ENGLISH;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultDescriptionServiceTest implements WithAssertions {

    @Mock
    private ResultDescriptionRepository repository;

    @InjectMocks
    private ResultDescriptionService underTest;

    @Test
    void givenService_whenGetResultDescriptionForDestinyByCategoryAndLanguage_thenReturnDescription() {
        when(repository.findByCategoryAndLanguage(anyString(), anyString())).thenReturn(TestData.sampleResultDescriptionOf(DESTINY));
        var resultDescription = underTest.findResultDescriptionByCategoryAndLanguage(DESTINY.getName(), ENGLISH.getValue());
        assertThat(resultDescription.getDescription()).contains("would be most likely to aspire toward");
    }

    @Test
    void givenService_whenGetResultDescriptionForPersonalityByCategoryAndLanguage_thenReturnDescription() {
        when(repository.findByCategoryAndLanguage(anyString(), anyString())).thenReturn(TestData.sampleResultDescriptionOf(PERSONALITY));
        var resultDescription = underTest.findResultDescriptionByCategoryAndLanguage(PERSONALITY.getName(), ENGLISH.getValue());
        assertThat(resultDescription.getDescription()).contains("behind-the-scenes or private you");
    }

    @Test
    void givenService_whenGetResultDescriptionForAttitudeByCategoryAndLanguage_thenReturnDescription() {
        when(repository.findByCategoryAndLanguage(anyString(), anyString())).thenReturn(TestData.sampleResultDescriptionOf(ATTITUDE));
        var resultDescription = underTest.findResultDescriptionByCategoryAndLanguage(ATTITUDE.getName(), ENGLISH.getValue());
        assertThat(resultDescription.getDescription()).contains("you react to circumstances in your youth");
    }

    @Test
    void givenService_whenGetResultDescriptionForCharacterByCategoryAndLanguage_thenReturnDescription() {
        when(repository.findByCategoryAndLanguage(anyString(), anyString())).thenReturn(TestData.sampleResultDescriptionOf(NumerologyValue.Category.CHARACTER));
        var resultDescription = underTest.findResultDescriptionByCategoryAndLanguage(NumerologyValue.Category.CHARACTER.getName(), ENGLISH.getValue());
        assertThat(resultDescription.getDescription()).contains("the same in your personal relations, too");
    }

    @Test
    void givenService_whenGetResultDescriptionForSoulUrgeByCategoryAndLanguage_thenReturnDescription() {
        when(repository.findByCategoryAndLanguage(anyString(), anyString())).thenReturn(TestData.sampleResultDescriptionOf(SOUL_URGE));
        var resultDescription = underTest.findResultDescriptionByCategoryAndLanguage(SOUL_URGE.getName(), ENGLISH.getValue());
        assertThat(resultDescription.getDescription()).contains("a view of your intuitive, soulful self");
    }

    @Test
    void givenService_whenGetResultDescriptionForHiddenAgendaByCategoryAndLanguage_thenReturnDescription() {
        when(repository.findByCategoryAndLanguage(anyString(), anyString())).thenReturn(TestData.sampleResultDescriptionOf(HIDDEN_AGENDA));
        var resultDescription = underTest.findResultDescriptionByCategoryAndLanguage(HIDDEN_AGENDA.getName(), ENGLISH.getValue());
        assertThat(resultDescription.getDescription()).contains("offers your innate (hidden) desires");
    }

    @Test
    void givenService_whenGetResultDescriptionForDivinePurposeByCategoryAndLanguage_thenReturnDescription() {
        when(repository.findByCategoryAndLanguage(anyString(), anyString())).thenReturn(TestData.sampleResultDescriptionOf(DIVINE_PURPOSE));
        var resultDescription = underTest.findResultDescriptionByCategoryAndLanguage(DIVINE_PURPOSE.getName(), ENGLISH.getValue());
        assertThat(resultDescription.getDescription()).contains("to achieve in your latter years of life");
    }

    @Test
    void givenService_whenGetResultDescriptionForPersonalYearByCategoryAndLanguage_thenReturnDescription() {
        when(repository.findByCategoryAndLanguage(anyString(), anyString())).thenReturn(TestData.sampleResultDescriptionOf(PERSONAL_YEAR));
        var resultDescription = underTest.findResultDescriptionByCategoryAndLanguage(PERSONAL_YEAR.getName(), ENGLISH.getValue());
        assertThat(resultDescription.getDescription()).contains("should expect about the current year");
    }

    @Test
    void givenService_whenGetResultDescriptionForLoveCompatibilityByCategoryAndLanguage_thenReturnDescription() {
        when(repository.findByCategoryAndLanguage(anyString(), anyString())).thenReturn(TestData.sampleResultDescriptionOf(LOVE_COMPATIBILITY));
        var resultDescription = underTest.findResultDescriptionByCategoryAndLanguage(LOVE_COMPATIBILITY.getName(), ENGLISH.getValue());
        assertThat(resultDescription.getDescription()).contains("love compatibility with another person");
    }

    @Test
    void givenService_whenGetResultDescriptionByLanguage_thenReturnDescription() {
        when(repository.findByLanguage(anyString())).thenReturn(TestData.getSampleResultDescriptions());
        var resultDescription = underTest.findResultDescriptionsByLanguage(ENGLISH.getValue());
        assertThat(resultDescription).isNotEmpty();
    }
}
