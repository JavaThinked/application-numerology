package com.javathinked.application.numerology.controller;

import com.javathinked.application.numerology.controller.dto.PersonDto;
import com.javathinked.application.numerology.controller.dto.ResultDto;
import com.javathinked.application.numerology.data.TestData;
import com.javathinked.application.numerology.service.NumerologyService;
import com.javathinked.application.numerology.service.core.NumerologyValue;
import com.javathinked.application.numerology.service.exception.NumerologyResultNotFoundException;
import com.javathinked.application.numerology.service.exception.NumerologyValidationException;
import com.javathinked.application.numerology.service.model.Person;
import com.javathinked.application.numerology.service.model.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.stream.Stream;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Category.*;
import static com.javathinked.application.numerology.service.core.NumerologyValue.Numbers.ONE;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NumerologyController.class)
class NumerologyControllerTest extends BaseControllerTest {

    @MockBean
    private NumerologyService service;
    private PersonDto personDto;
    private Person person;

    @BeforeEach
    void setUp() {
        personDto = TestData.createPersonDto();
        person = TestData.createPerson();
    }

    @DisplayName("Parametrized test for Numerology Controller - Status 200")
    @ParameterizedTest(name = "#{index} - Parametrized test for {0}")
    @MethodSource("getControllerTestParameters")
    void givenController_whenComputeNumber_thenReturnResultWithStatus_200(NumerologyValue.Category category, String path, String message) throws Exception {
        var result = TestData.sampleResultOf(category);
        when(mapper.performMapping(personDto, Person.class)).thenReturn(person);
        when(service.computeNumber(category, person)).thenReturn(result);
        when(mapper.performMapping(result, ResultDto.class)).thenReturn(mapToResultDto(result));

        mockMvc.perform(post(TestData.API_V1_BASE_URL + path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.statusValue", is(HttpStatus.OK.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.OK.getReasonPhrase())),
                        jsonPath("$.description", containsString(category.getDescription())),
                        jsonPath("$.detail.number", is(ONE.getValue())),
                        jsonPath("$.detail.message", containsString(message))
                );
    }

    @DisplayName("Parametrized test for Numerology Controller - Status 400")
    @ParameterizedTest(name = "#{index} - Parametrized test for {0}")
    @MethodSource("getControllerTestParameters")
    void givenController_whenComputeNumberWithBadParameters_thenReturnResultWithStatus_400(NumerologyValue.Category category, String path) throws Exception {
        when(mapper.performMapping(personDto, Person.class)).thenReturn(person);
        when(service.computeNumber(category, person)).thenThrow(NumerologyValidationException.class);

        mockMvc.perform(post(TestData.API_V1_BASE_URL + path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto)))
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.statusValue", is(HttpStatus.BAD_REQUEST.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.BAD_REQUEST.getReasonPhrase())),
                        jsonPath("$.description", containsString(category.getDescription()))
                );
    }

    @DisplayName("Parametrized test for Numerology Controller - Status 404")
    @ParameterizedTest(name = "#{index} - Parametrized test for {0}")
    @MethodSource("getControllerTestParameters")
    void givenController_whenComputeNumberAndResultNotFound_thenReturnResultWithStatus_404(NumerologyValue.Category category, String path) throws Exception {
        when(mapper.performMapping(personDto, Person.class)).thenReturn(person);
        when(service.computeNumber(category, person)).thenThrow(NumerologyResultNotFoundException.class);

        mockMvc.perform(post(TestData.API_V1_BASE_URL + path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto)))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.statusValue", is(HttpStatus.NOT_FOUND.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.NOT_FOUND.getReasonPhrase())),
                        jsonPath("$.description", containsString(category.getDescription()))
                );
    }

    static Stream<Arguments> getControllerTestParameters() {
        return Stream.of(
                arguments(DESTINY, "/destiny", "the wholly independent trail-blazer"),
                arguments(PERSONALITY, "/personality", "sets the stage for your success"),
                arguments(ATTITUDE, "/attitude", "when pushed in a different direction"),
                arguments(NumerologyValue.Category.CHARACTER, "/character", "your goals qualifies you as a leader"),
                arguments(SOUL_URGE, "/soul-urge", "that engenders compassion for all"),
                arguments(HIDDEN_AGENDA, "/hidden-agenda", "and unique concepts and things"),
                arguments(DIVINE_PURPOSE, "/divine-purpose", "a concept, a product, a foundation"),
                arguments(PERSONAL_YEAR, "/personal-year", "you will make a profitable investment")
        );
    }

    @Test
    void givenController_whenComputeLoveCompatibility_thenReturnResultWithStatus_200() throws Exception {
        var result = TestData.sampleResultOf(LOVE_COMPATIBILITY);
        when(mapper.performMapping(personDto, Person.class)).thenReturn(person);
        when(service.computeNumber(LOVE_COMPATIBILITY, person, person)).thenReturn(result);
        when(mapper.performMapping(result, ResultDto.class)).thenReturn(mapToResultDto(result));

        var requestDto = new PersonDto[]{personDto, personDto};
        mockMvc.perform(post(TestData.API_V1_BASE_URL + "/love-compatibility")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.statusValue", is(HttpStatus.OK.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.OK.getReasonPhrase())),
                        jsonPath("$.description", containsString(LOVE_COMPATIBILITY.getDescription())),
                        jsonPath("$.detail.number", is(ONE.getValue())),
                        jsonPath("$.detail.message", containsString("most likely to aspire toward"))
                );
    }

    @Test
    void givenController_whenComputeLoveCompatibilityWithBadParameters_thenReturnResultWithStatus_400() throws Exception {
        when(mapper.performMapping(personDto, Person.class)).thenReturn(person);
        when(service.computeNumber(LOVE_COMPATIBILITY, person, person)).thenThrow(NumerologyValidationException.class);

        var requestDto = new PersonDto[]{personDto, personDto};
        mockMvc.perform(post(TestData.API_V1_BASE_URL + "/love-compatibility")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.statusValue", is(HttpStatus.BAD_REQUEST.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.BAD_REQUEST.getReasonPhrase())),
                        jsonPath("$.description", containsString(LOVE_COMPATIBILITY.getDescription()))
                );
    }

    @Test
    void givenController_whenComputeLoveCompatibilityWithBadParameters_thenReturnResultWithStatus_404() throws Exception {
        when(mapper.performMapping(personDto, Person.class)).thenReturn(person);
        when(service.computeNumber(LOVE_COMPATIBILITY, person, person)).thenThrow(NumerologyResultNotFoundException.class);

        var requestDto = new PersonDto[]{personDto, personDto};
        mockMvc.perform(post(TestData.API_V1_BASE_URL + "/love-compatibility")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.statusValue", is(HttpStatus.NOT_FOUND.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.NOT_FOUND.getReasonPhrase())),
                        jsonPath("$.description", containsString(LOVE_COMPATIBILITY.getDescription()))
                );
    }

    private ResultDto mapToResultDto(Result result) {
        var resultDto = new ResultDto();
        resultDto.setId(result.getId());
        resultDto.setCategory(result.getCategory());
        resultDto.setNumber(result.getNumber());
        resultDto.setMessage(result.getMessage());
        resultDto.setLanguage(result.getLanguage());
        return resultDto;
    }
}