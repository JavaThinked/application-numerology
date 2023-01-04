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
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class ResultDescriptionRepositoryTest implements WithAssertions {

    private final ResultDescriptionRepository repository;

    @Autowired
    ResultDescriptionRepositoryTest(ResultDescriptionRepository repository) {
        this.repository = repository;
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescription_thenReturnDescriptions() {
        var descriptions = repository.findAll();
        assertThat(descriptions).isNotEmpty();
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescriptionOfDestiny_thenReturnDescriptionInEnglish() {
        var resultDescription = repository.findByCategoryAndLanguage(DESTINY.getName(), ENGLISH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(DESTINY.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("most likely to aspire toward"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescriptionOfDestiny_thenReturnDescriptionInFrench() {
        var resultDescription = repository.findByCategoryAndLanguage(DESTINY.getName(), FRENCH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(DESTINY.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("vous seriez le plus susceptible"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescriptionOfPersonality_thenReturnDescriptionInEnglish() {
        var resultDescription = repository.findByCategoryAndLanguage(PERSONALITY.getName(), ENGLISH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(PERSONALITY.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("behind-the-scenes or private you"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescriptionOfPersonality_thenReturnDescriptionInFrench() {
        var resultDescription = repository.findByCategoryAndLanguage(PERSONALITY.getName(), FRENCH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(PERSONALITY.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("décrit les coulisses ou vous privé"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescriptionOfAttitude_thenReturnDescriptionInEnglish() {
        var resultDescription = repository.findByCategoryAndLanguage(ATTITUDE.getName(), ENGLISH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(ATTITUDE.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("react to circumstances in your youth"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultAttitudeOfAttitude_thenReturnDescriptionInFrench() {
        var resultDescription = repository.findByCategoryAndLanguage(ATTITUDE.getName(), FRENCH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(ATTITUDE.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("aux circonstances de votre jeunesse"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescriptionOfCharacter_thenReturnDescriptionInEnglish() {
        var resultDescription = repository.findByCategoryAndLanguage(NumerologyValue.Category.CHARACTER.getName(), ENGLISH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(NumerologyValue.Category.CHARACTER.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("the same in your personal relations, too"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultAttitudeOfCharacter_thenReturnDescriptionInFrench() {
        var resultDescription = repository.findByCategoryAndLanguage(NumerologyValue.Category.CHARACTER.getName(), FRENCH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(NumerologyValue.Category.CHARACTER.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("même chose dans vos relations personnelles"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescriptionOfSoulUrge_thenReturnDescriptionInEnglish() {
        var resultDescription = repository.findByCategoryAndLanguage(SOUL_URGE.getName(), ENGLISH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(SOUL_URGE.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("of your intuitive, soulful self"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultAttitudeOfSoulUrge_thenReturnDescriptionInFrench() {
        var resultDescription = repository.findByCategoryAndLanguage(SOUL_URGE.getName(), FRENCH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(SOUL_URGE.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("votre moi intuitif et émouvant"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescriptionOfHiddenAgenda_thenReturnDescriptionInEnglish() {
        var resultDescription = repository.findByCategoryAndLanguage(HIDDEN_AGENDA.getName(), ENGLISH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(HIDDEN_AGENDA.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("innate (hidden) desires"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultAttitudeOfHiddenAgenda_thenReturnDescriptionInFrench() {
        var resultDescription = repository.findByCategoryAndLanguage(HIDDEN_AGENDA.getName(), FRENCH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(HIDDEN_AGENDA.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("offre vos désirs innés (cachés)"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescriptionOfDivinePurpose_thenReturnDescriptionInEnglish() {
        var resultDescription = repository.findByCategoryAndLanguage(DIVINE_PURPOSE.getName(), ENGLISH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(DIVINE_PURPOSE.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("in your latter years of life"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultAttitudeOfPersonalYear_thenReturnDescriptionInFrench() {
        var resultDescription = repository.findByCategoryAndLanguage(PERSONAL_YEAR.getName(), FRENCH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(PERSONAL_YEAR.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("quoi vous devez vous attendre pour"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescriptionOfPersonalYear_thenReturnDescriptionInEnglish() {
        var resultDescription = repository.findByCategoryAndLanguage(PERSONAL_YEAR.getName(), ENGLISH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(PERSONAL_YEAR.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("expect about the current year"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultAttitudeOfDivinePurpose_thenReturnDescriptionInFrench() {
        var resultDescription = repository.findByCategoryAndLanguage(DIVINE_PURPOSE.getName(), FRENCH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(DIVINE_PURPOSE.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("dans vos dernières années de vie"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultDescriptionOfLoveCompatibility_thenReturnDescriptionInEnglish() {
        var resultDescription = repository.findByCategoryAndLanguage(LOVE_COMPATIBILITY.getName(), ENGLISH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(LOVE_COMPATIBILITY.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("compatibility with another person"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(ENGLISH.getValue())
        );
    }

    @Test
    void givenRepository_whenGetNumerologyResultAttitudeOfLoveCompatibility_thenReturnDescriptionInFrench() {
        var resultDescription = repository.findByCategoryAndLanguage(LOVE_COMPATIBILITY.getName(), FRENCH.getValue());
        assertAll(
                () -> assertThat(resultDescription).isNotNull(),
                () -> assertThat(resultDescription.getCategory()).isEqualTo(LOVE_COMPATIBILITY.getName()),
                () -> assertThat(resultDescription.getDescription()).contains("amoureuse avec une autre personne"),
                () -> assertThat(resultDescription.getLanguage()).isEqualTo(FRENCH.getValue())
        );
    }

}