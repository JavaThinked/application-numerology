package com.javathinked.application.numerology.repository;

import com.javathinked.application.numerology.service.core.NumerologyValue;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Category.*;
import static com.javathinked.application.numerology.service.core.NumerologyValue.Language.ENGLISH;
import static com.javathinked.application.numerology.service.core.NumerologyValue.Language.FRENCH;
import static com.javathinked.application.numerology.service.core.NumerologyValue.Numbers.ONE;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class ResultRepositoryTest implements WithAssertions {

    @Autowired
    private ResultRepository repository;

    @Test
    void givenRepository_whenGetNumerologyResult_thenReturnResults() {
        var results = repository.findAll();
        assertAll(
                () -> assertThat(results).isNotEmpty(),
                () -> assertThat(results).anyMatch(result -> DESTINY.getCategory().equals(result.getCategory())),
                () -> assertThat(results).anyMatch(result -> PERSONALITY.getCategory().equals(result.getCategory())),
                () -> assertThat(results).anyMatch(result -> ATTITUDE.getCategory().equals(result.getCategory())),
                () -> assertThat(results).anyMatch(result -> NumerologyValue.Category.CHARACTER.getCategory().equals(result.getCategory())),
                () -> assertThat(results).anyMatch(result -> SOUL_URGE.getCategory().equals(result.getCategory())),
                () -> assertThat(results).anyMatch(result -> HIDDEN_AGENDA.getCategory().equals(result.getCategory())),
                () -> assertThat(results).anyMatch(result -> DIVINE_PURPOSE.getCategory().equals(result.getCategory())),
                () -> assertThat(results).anyMatch(result -> PERSONAL_YEAR.getCategory().equals(result.getCategory())),
                () -> assertThat(results).anyMatch(result -> LOVE_COMPATIBILITY.getCategory().equals(result.getCategory()))
        );
    }

    @Test
    void givenPerson_whenComputeDestinyAndResultIsONE_thenReturnsResultInEnglish() {
        var destiny = repository.findResultByCategoryAndNumberAndLanguage(DESTINY.getCategory(),
                ONE.getValue(),
                ENGLISH.getValue());

        assertAll(
                () -> assertThat(destiny.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(destiny.getMessage()).contains("the wholly independent trail-blazer"),
                () -> assertThat(destiny.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeDestinyAndResultIsONE_thenReturnsResultInFrench() {
        var destiny = repository.findResultByCategoryAndNumberAndLanguage(DESTINY.getCategory(),
                ONE.getValue(),
                FRENCH.getValue());

        assertAll(
                () -> assertThat(destiny.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(destiny.getMessage()).contains("du pionnier totalement indépendant"),
                () -> assertThat(destiny.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputePersonalityAndResultIsONE_thenReturnsResultInEnglish() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(PERSONALITY.getCategory(),
                ONE.getValue(),
                ENGLISH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("focus sets the stage for your success"),
                () -> assertThat(personality.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputePersonalityAndResultIsONE_thenReturnsResultInFrench() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(PERSONALITY.getCategory(),
                ONE.getValue(),
                FRENCH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("le terrain pour votre succès"),
                () -> assertThat(personality.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeAttitudeAndResultIsONE_thenReturnsResultInEnglish() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(ATTITUDE.getCategory(),
                ONE.getValue(),
                ENGLISH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("stubborn when pushed in a different direction"),
                () -> assertThat(personality.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeAttitudeAndResultIsONE_thenReturnsResultInFrench() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(ATTITUDE.getCategory(),
                ONE.getValue(),
                FRENCH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("vous êtes poussé dans une direction différente"),
                () -> assertThat(personality.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeCharacterAndResultIsONE_thenReturnsResultInEnglish() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(NumerologyValue.Category.CHARACTER.getCategory(),
                ONE.getValue(),
                ENGLISH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("on your goals qualifies you as a leader"),
                () -> assertThat(personality.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeCharacterAndResultIsONE_thenReturnsResultInFrench() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(NumerologyValue.Category.CHARACTER.getCategory(),
                ONE.getValue(),
                FRENCH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("vous qualifie de leader"),
                () -> assertThat(personality.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeSoulUrgeAndResultIsONE_thenReturnsResultInEnglish() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(SOUL_URGE.getCategory(),
                ONE.getValue(),
                ENGLISH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("that engenders compassion for all"),
                () -> assertThat(personality.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeSoulUrgeAndResultIsONE_thenReturnsResultInFrench() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(SOUL_URGE.getCategory(),
                ONE.getValue(),
                FRENCH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("qui engendre la compassion pour tous"),
                () -> assertThat(personality.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeHiddenAgendaAndResultIsONE_thenReturnsResultInEnglish() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(HIDDEN_AGENDA.getCategory(),
                ONE.getValue(),
                ENGLISH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("and unique concepts and things"),
                () -> assertThat(personality.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeHiddenAgendaAndResultIsONE_thenReturnsResultInFrench() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(HIDDEN_AGENDA.getCategory(),
                ONE.getValue(),
                FRENCH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("des choses originaux et uniques"),
                () -> assertThat(personality.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeDivinePurposeAndResultIsONE_thenReturnsResultInEnglish() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(DIVINE_PURPOSE.getCategory(),
                ONE.getValue(),
                ENGLISH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("a concept, a product, a foundation"),
                () -> assertThat(personality.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeDivinePurposeAndResultIsONE_thenReturnsResultInFrench() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(DIVINE_PURPOSE.getCategory(),
                ONE.getValue(),
                FRENCH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("un concept, un produit, une fondation"),
                () -> assertThat(personality.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputePersonalYearAndResultIsONE_thenReturnsResultInEnglish() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(PERSONAL_YEAR.getCategory(),
                ONE.getValue(),
                ENGLISH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("you will make a profitable investment"),
                () -> assertThat(personality.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputePersonalYearAndResultIsONE_thenReturnsResultInFrench() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(PERSONAL_YEAR.getCategory(),
                ONE.getValue(),
                FRENCH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("vous réaliserez un investissement profitable"),
                () -> assertThat(personality.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeLoveCompatibilityAndResultIsONE_thenReturnsResultInEnglish() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(LOVE_COMPATIBILITY.getCategory(),
                ONE.getValue(),
                ENGLISH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("keep the flame alive on a daily basis"),
                () -> assertThat(personality.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenPerson_whenComputeLoveCompatibilityAndResultIsONE_thenReturnsResultInFrench() {
        var personality = repository.findResultByCategoryAndNumberAndLanguage(LOVE_COMPATIBILITY.getCategory(),
                ONE.getValue(),
                FRENCH.getValue());

        assertAll(
                () -> assertThat(personality.getNumber()).isEqualTo(ONE.getValue()),
                () -> assertThat(personality.getMessage()).contains("savoir entretenir la flamme au quotidien"),
                () -> assertThat(personality.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }
}