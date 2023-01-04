package com.javathinked.application.numerology.service;

import com.javathinked.application.numerology.data.TestData;
import com.javathinked.application.numerology.repository.ResultRepository;
import com.javathinked.application.numerology.service.core.NumerologyComputer;
import com.javathinked.application.numerology.service.core.NumerologyModelValidator;
import com.javathinked.application.numerology.service.core.NumerologyValue;
import com.javathinked.application.numerology.service.model.Person;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.stream.Stream;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Category.*;
import static com.javathinked.application.numerology.service.core.NumerologyValue.Language.ENGLISH;
import static com.javathinked.application.numerology.service.core.NumerologyValue.Numbers.ONE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NumerologyServiceTest implements WithAssertions {

    @Mock
    private Map<String, NumerologyComputer> computerMap;

    @Mock
    private NumerologyModelValidator<Person> validator;

    @Mock
    private ResultRepository repository;

    @InjectMocks
    private NumerologyService underTest;

    private Person person;

    @BeforeEach
    void setUp() {
        person = TestData.createPerson();
    }

    @DisplayName("Parametrized test for Numerology Service")
    @ParameterizedTest(name = "#{index} - Parametrized test for {0}")
    @MethodSource("getServiceTestParameters")
    void givenService_whenComputeNumber_thenReturnResult(NumerologyValue.Category category, String message) {
        when(computerMap.get(category.getBeanName())).thenReturn(TestData.computerFactory(category));
        when(repository.findResultByCategoryAndNumberAndLanguage(anyString(), anyInt(), anyString()))
                .thenReturn(TestData.sampleResultOf(category));
        var result = underTest.computeNumber(category, person);
        assertAll(
                () -> assertThat(result.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(result.getCategory()).isEqualTo(category.getName()),
                () -> assertThat(result.getMessage()).contains(message),
                () -> assertThat(result.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenService_whenComputeLoveCompatibility_thenReturnResult() {
        when(computerMap.get(LOVE_COMPATIBILITY.getBeanName())).thenReturn(TestData.computerFactory(PERSONALITY));
        when(repository.findResultByCategoryAndNumberAndLanguage(anyString(), anyInt(), anyString()))
                .thenReturn(TestData.sampleResultOf(LOVE_COMPATIBILITY));
        var result = underTest.computeNumber(LOVE_COMPATIBILITY, person, person);
        assertAll(
                () -> assertThat(result.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(result.getCategory()).isEqualTo(LOVE_COMPATIBILITY.getName()),
                () -> assertThat(result.getMessage()).contains("you would be most likely to aspire toward"),
                () -> assertThat(result.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    static Stream<Arguments> getServiceTestParameters() {
        return Stream.of(
                arguments(DESTINY, "the wholly independent trail-blazer"),
                arguments(PERSONALITY, "sets the stage for your success"),
                arguments(ATTITUDE, "when pushed in a different direction"),
                arguments(NumerologyValue.Category.CHARACTER, "your goals qualifies you as a leader"),
                arguments(SOUL_URGE, "that engenders compassion for all"),
                arguments(HIDDEN_AGENDA, "and unique concepts and things"),
                arguments(DIVINE_PURPOSE, "a concept, a product, a foundation"),
                arguments(PERSONAL_YEAR, "you will make a profitable investment")
        );
    }
}