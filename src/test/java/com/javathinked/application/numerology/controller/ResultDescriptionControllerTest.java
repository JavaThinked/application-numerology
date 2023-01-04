package com.javathinked.application.numerology.controller;

import com.javathinked.application.numerology.controller.dto.ResultDescriptionDto;
import com.javathinked.application.numerology.data.TestData;
import com.javathinked.application.numerology.service.ResultDescriptionService;
import com.javathinked.application.numerology.service.core.NumerologyValue;
import com.javathinked.application.numerology.service.exception.NumerologyCustomException;
import com.javathinked.application.numerology.service.exception.NumerologyResultNotFoundException;
import com.javathinked.application.numerology.service.model.ResultDescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResultDescriptionController.class)
class ResultDescriptionControllerTest extends BaseControllerTest {

    @MockBean
    private ResultDescriptionService service;
    private ResultDescriptionDto resultDescriptionDto;
    private ResultDescription resultDescription;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenController_whenFindingResultDescriptionsByLanguage_thenReturnResultWithStatus_200() throws Exception {
        when(service.findResultDescriptionsByLanguage(anyString())).thenReturn(TestData.getSampleResultDescriptions());

        mockMvc.perform(get(TestData.API_V1_BASE_URL + "/descriptions/{language}", NumerologyValue.Language.ENGLISH.getValue())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.statusValue", is(HttpStatus.OK.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.OK.getReasonPhrase())),
                        jsonPath("$.description", containsString("Descriptions found for en"))
                );
    }

    @Test
    void givenController_whenFindingResultDescriptionsByLanguageWithBadParameters_thenReturnResultWithStatus_400() throws Exception {
        when(service.findResultDescriptionsByLanguage(anyString())).thenThrow(NumerologyCustomException.class);

        mockMvc.perform(get(TestData.API_V1_BASE_URL + "/descriptions/{language}", "bad_language")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.statusValue", is(HttpStatus.BAD_REQUEST.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.BAD_REQUEST.getReasonPhrase()))
                );
    }

    @Test
    void givenController_whenFindingResultDescriptionsByLanguageAndResultNotFound_thenReturnResultWithStatus_404() throws Exception {
        when(service.findResultDescriptionsByLanguage(anyString())).thenThrow(NumerologyResultNotFoundException.class);

        mockMvc.perform(get(TestData.API_V1_BASE_URL + "/descriptions/{language}", "good_language")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.statusValue", is(HttpStatus.NOT_FOUND.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.NOT_FOUND.getReasonPhrase()))
                );
    }

    @Test
    void givenController_whenFindingResultDescriptionByCategoryAndLanguage_thenReturnResultWithStatus_200() throws Exception {
        when(service.findResultDescriptionsByLanguage(anyString())).thenReturn(TestData.getSampleResultDescriptions());

        mockMvc.perform(get(TestData.API_V1_BASE_URL + "/description/{category}/{language}", NumerologyValue.Category.DESTINY.getCategory(), NumerologyValue.Language.ENGLISH.getValue())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.statusValue", is(HttpStatus.OK.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.OK.getReasonPhrase())),
                        jsonPath("$.description", containsString("Descriptions found for destiny"))
                );
    }

    @Test
    void givenController_whenFindingResultDescriptionByCategoryAndLanguageWithBadParameters_thenReturnResultWithStatus_400() throws Exception {
        when(service.findResultDescriptionByCategoryAndLanguage(anyString(), anyString())).thenThrow(NumerologyCustomException.class);

        mockMvc.perform(get(TestData.API_V1_BASE_URL + "/description/{category}/{language}", "bad_category", "bad_language")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.statusValue", is(HttpStatus.BAD_REQUEST.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.BAD_REQUEST.getReasonPhrase()))
                );
    }

    @Test
    void givenController_whenFindingResultDescriptionByCategoryAndLanguageAndResultNotFound_thenReturnResultWithStatus_404() throws Exception {
        when(service.findResultDescriptionByCategoryAndLanguage(anyString(), anyString())).thenThrow(NumerologyResultNotFoundException.class);

        mockMvc.perform(get(TestData.API_V1_BASE_URL + "/description/{category}/{language}", "good_category", "good_language")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.statusValue", is(HttpStatus.NOT_FOUND.value())),
                        jsonPath("$.statusReasonPhrase", is(HttpStatus.NOT_FOUND.getReasonPhrase()))
                );
    }
}