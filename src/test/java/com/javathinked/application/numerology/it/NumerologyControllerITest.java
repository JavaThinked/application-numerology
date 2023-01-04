package com.javathinked.application.numerology.it;

import com.javathinked.application.numerology.controller.Response;
import com.javathinked.application.numerology.controller.dto.PersonDto;
import com.javathinked.application.numerology.data.TestData;
import com.javathinked.application.numerology.service.core.NumerologyValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.stream.Stream;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Category.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NumerologyControllerITest extends BaseIntegrationTest {

    private PersonDto personDto;

    @BeforeEach
    void setUp() {
        super.setUp();
        personDto = TestData.createPersonDto();
    }

    @DisplayName("Parametrized test for Numerology Integration - Status 200")
    @ParameterizedTest(name = "#{index} - Parametrized test for {0}")
    @MethodSource("getIntegrationTestParameters")
    void integrationTest_For_Numerology_Status_200(NumerologyValue.Category category, String path) {
        webTestClient.post()
                .uri(url + path)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(personDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Response.class)
                .value(response -> assertAll(
                        () -> assertThat(response.getStatusValue()).isEqualTo(HttpStatus.OK.value()),
                        () -> assertThat(response.getStatusReasonPhrase()).isEqualTo(HttpStatus.OK.getReasonPhrase()),
                        () -> assertThat(response.getDescription()).isEqualTo(category.getDescription())
                ));
    }

    static Stream<Arguments> getIntegrationTestParameters() {
        return Stream.of(
                arguments(DESTINY, "/destiny"),
                arguments(PERSONALITY, "/personality"),
                arguments(ATTITUDE, "/attitude"),
                arguments(NumerologyValue.Category.CHARACTER, "/character"),
                arguments(SOUL_URGE, "/soul-urge"),
                arguments(HIDDEN_AGENDA, "/hidden-agenda"),
                arguments(DIVINE_PURPOSE, "/divine-purpose"),
                arguments(PERSONAL_YEAR, "/personal-year")
        );
    }

    @DisplayName("Parametrized test for Numerology Integration - Status 400")
    @ParameterizedTest(name = "#{index} - Parametrized test for {0}")
    @MethodSource("getIntegrationTestParametersForErrorStatus")
    void integrationTest_For_Numerology_Status_400(String path) {
        personDto.setFirstName("");
        personDto.setLastName("");
        webTestClient.post()
                .uri(url + path)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(personDto)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Response.class)
                .value(response -> assertAll(
                        () -> assertThat(response.getStatusValue()).isEqualTo(HttpStatus.BAD_REQUEST.value()),
                        () -> assertThat(response.getStatusReasonPhrase()).isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase())
                ));
    }

    static Stream<Arguments> getIntegrationTestParametersForErrorStatus() {
        return Stream.of(
                arguments("/destiny"),
                arguments("/personality"),
                arguments("/attitude"),
                arguments("/character"),
                arguments("/soul-urge"),
                arguments("/hidden-agenda"),
                arguments("/divine-purpose"),
                arguments("/personal-year")
        );
    }
}
