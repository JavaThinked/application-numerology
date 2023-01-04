package com.javathinked.application.numerology.data;

import com.javathinked.application.numerology.controller.dto.PersonDto;
import com.javathinked.application.numerology.controller.dto.ResultDescriptionDto;
import com.javathinked.application.numerology.service.core.Character;
import com.javathinked.application.numerology.service.core.*;
import com.javathinked.application.numerology.service.model.Formula;
import com.javathinked.application.numerology.service.model.Person;
import com.javathinked.application.numerology.service.model.Result;
import com.javathinked.application.numerology.service.model.ResultDescription;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Category.*;
import static com.javathinked.application.numerology.service.core.NumerologyValue.Language.ENGLISH;

@UtilityClass
public class TestData {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "EMMANUEL";
    public static final String LAST_NAME = "SOMBUGMA";
    public static final String EMAIL = "emmanuel.email@mail.com";
    public static final String PHONE_NUMBER = "460-555-4020";
    public static final String LANGUAGE = "en";
    public static final int DAY = 3;
    public static final int MONTH = 9;
    public static final int YEAR = 1997;
    private String API_VERSION_V1 = "/v1";
    public String API_V1_BASE_URL = API_VERSION_V1 + "/numerology";

    public PersonDto createPersonDto() {
        var personDto = new PersonDto();
        personDto.setId(ID);
        personDto.setFirstName(FIRST_NAME);
        personDto.setLastName(LAST_NAME);
        personDto.setDay(DAY);
        personDto.setMonth(MONTH);
        personDto.setYear(YEAR);
        personDto.setEmail(EMAIL);
        personDto.setPhoneNumber(PHONE_NUMBER);
        personDto.setLanguage(LANGUAGE);
        return personDto;
    }

    public Person createPerson() {
        var person = new Person();
        person.setId(ID);
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setDay(DAY);
        person.setMonth(MONTH);
        person.setYear(YEAR);
        person.setEmail(EMAIL);
        person.setPhoneNumber(PHONE_NUMBER);
        person.setLanguage(LANGUAGE);
        return person;
    }

    public Result sampleResultOf(NumerologyValue.Category category) {
        var result = new Result();
        switch (category) {
            case DESTINY:
                result.setCategory(DESTINY.getCategory());
                result.setNumber(1);
                result.setMessage("This is the path of the risk-taker, the entrepreneur, the wholly independent trail-blazer.");
                result.setLanguage(ENGLISH.getValue());
                break;
            case PERSONALITY:
                result.setCategory(PERSONALITY.getCategory());
                result.setNumber(1);
                result.setMessage("You are dynamic, strong-willed and a natural leader, and your single-minded focus sets the stage for your success.");
                result.setLanguage(ENGLISH.getValue());
                break;
            case ATTITUDE:
                result.setCategory(ATTITUDE.getCategory());
                result.setNumber(1);
                result.setMessage("You are born with a strong will and independent nature. You prefer to make your own choices and are stubborn when pushed in a different direction.");
                result.setLanguage(ENGLISH.getValue());
                break;
            case CHARACTER:
                result.setCategory(CHARACTER.getCategory());
                result.setNumber(1);
                result.setMessage("Possessing an original and innovative mind, with a steadfast focus on your goals qualifies you as a leader.");
                result.setLanguage(ENGLISH.getValue());
                break;
            case SOUL_URGE:
                result.setCategory(SOUL_URGE.getCategory());
                result.setNumber(1);
                result.setMessage("Your Soul''s Urge is to be innovative, creative, and lead the masses to a higher realm of consciousness and understanding that engenders compassion for all.");
                result.setLanguage(ENGLISH.getValue());
                break;
            case HIDDEN_AGENDA:
                result.setCategory(HIDDEN_AGENDA.getCategory());
                result.setNumber(1);
                result.setMessage("This agenda incites an innate desire to be independent, to lead, to innovate, and to create original and unique concepts and things.");
                result.setLanguage(ENGLISH.getValue());
                break;
            case DIVINE_PURPOSE:
                result.setCategory(DIVINE_PURPOSE.getCategory());
                result.setNumber(1);
                result.setMessage("Your Divine Purpose is to leave behind a legacy of something you pioneered - a concept, a product, a foundation.");
                result.setLanguage(ENGLISH.getValue());
                break;
            case PERSONAL_YEAR:
                result.setCategory(PERSONAL_YEAR.getCategory());
                result.setNumber(1);
                result.setMessage("The number 1 puts you on the path to personal advancement. At work, you will start new projects. In love, you will meet a surprise. By investing money, you will make a profitable investment.");
                result.setLanguage(ENGLISH.getValue());
                break;
            case LOVE_COMPATIBILITY:
                result.setCategory(LOVE_COMPATIBILITY.getCategory());
                result.setNumber(1);
                result.setMessage("This is the most influential number in relation to your personal motivations, what and who you like in your surroundings, and the career(s) you would be most likely to aspire toward.");
                result.setLanguage(ENGLISH.getValue());
                break;
            default:
                throw new IllegalArgumentException("The category: " + category + " is not found");
        }
        return result;
    }

    public Formula formulaOf(NumerologyValue.Category category) {
        var formula = new Formula();
        switch (category) {
            case DESTINY:
                formula.setCategory(DESTINY.getCategory());
                formula.setExpression("Month + Day + Year");
                break;
            case PERSONALITY:
                formula.setCategory(PERSONALITY.getCategory());
                formula.setExpression("Day");
                break;
            case ATTITUDE:
                formula.setCategory(ATTITUDE.getCategory());
                formula.setExpression("Month + Day");
                break;
            case CHARACTER:
                formula.setCategory(CHARACTER.getCategory());
                formula.setExpression("Sum of Letters");
                break;
            case SOUL_URGE:
                formula.setCategory(SOUL_URGE.getCategory());
                formula.setExpression("Sum of Vowels");
                break;
            case HIDDEN_AGENDA:
                formula.setCategory(HIDDEN_AGENDA.getCategory());
                formula.setExpression("Sum of Consonants");
                break;
            case DIVINE_PURPOSE:
                formula.setCategory(DIVINE_PURPOSE.getCategory());
                formula.setExpression("Destiny + Character");
                break;
            case PERSONAL_YEAR:
                formula.setCategory(PERSONAL_YEAR.getCategory());
                formula.setExpression("Month + Day + Current Year");
                break;
            case LOVE_COMPATIBILITY:
                formula.setCategory(LOVE_COMPATIBILITY.getCategory());
                formula.setExpression("Sum last names");
                break;
            default:
                throw new IllegalArgumentException("The category: " + category + " is not found");
        }
        return formula;
    }

    public ResultDescription sampleResultDescriptionOf(NumerologyValue.Category category) {
        var resultDescription = new ResultDescription();
        resultDescription.setDescription(ENGLISH.getValue());
        switch (category) {
            case DESTINY:
                resultDescription.setCategory(DESTINY.getCategory());
                resultDescription.setDescription("This is the most influential number in relation to your personal motivations, what and who you like in your surroundings, and the career(s) you would be most likely to aspire toward.");
                break;
            case PERSONALITY:
                resultDescription.setCategory(PERSONALITY.getCategory());
                resultDescription.setDescription("This number describes the behind-the-scenes or private you.");
                break;
            case ATTITUDE:
                resultDescription.setCategory(ATTITUDE.getCategory());
                resultDescription.setDescription("This number is most powerful in the first 30-35 years of your life and represents the how and why you react to circumstances in your youth.");
                break;
            case CHARACTER:
                resultDescription.setCategory(CHARACTER.getCategory());
                resultDescription.setDescription("This number represents primarily your public and social qualities, but you would also exhibit much of the same in your personal relations, too.");
                break;
            case SOUL_URGE:
                resultDescription.setCategory(SOUL_URGE.getCategory());
                resultDescription.setDescription("This influence presents a view of your intuitive, soulful self.");
                break;
            case HIDDEN_AGENDA:
                resultDescription.setCategory(HIDDEN_AGENDA.getCategory());
                resultDescription.setDescription("This vibration offers your innate (hidden) desires.");
                break;
            case DIVINE_PURPOSE:
                resultDescription.setCategory(DIVINE_PURPOSE.getCategory());
                resultDescription.setDescription("This is your farewell-performance number vibration. It is what your Soulful Self has chosen to achieve in your latter years of life.");
                break;
            case PERSONAL_YEAR:
                resultDescription.setCategory(PERSONAL_YEAR.getCategory());
                resultDescription.setDescription("This is about you should expect about the current year.");
                break;
            case LOVE_COMPATIBILITY:
                resultDescription.setCategory(LOVE_COMPATIBILITY.getCategory());
                resultDescription.setDescription("This used to determine your love compatibility with another person.");
                break;
            default:
                throw new IllegalArgumentException("The category: " + category + " is not found");
        }
        return resultDescription;
    }

    public ResultDescriptionDto sampleResultDescriptionDtoOf(NumerologyValue.Category category) {
        var resultDescriptionDto = new ResultDescriptionDto();
        resultDescriptionDto.setDescription(ENGLISH.getValue());
        switch (category) {
            case DESTINY:
                resultDescriptionDto.setCategory(DESTINY.getCategory());
                resultDescriptionDto.setDescription("This is the most influential number in relation to your personal motivations, what and who you like in your surroundings, and the career(s) you would be most likely to aspire toward.");
                break;
            case PERSONALITY:
                resultDescriptionDto.setCategory(PERSONALITY.getCategory());
                resultDescriptionDto.setDescription("This number describes the behind-the-scenes or private you.");
                break;
            case ATTITUDE:
                resultDescriptionDto.setCategory(ATTITUDE.getCategory());
                resultDescriptionDto.setDescription("This number is most powerful in the first 30-35 years of your life and represents the how and why you react to circumstances in your youth.");
                break;
            case CHARACTER:
                resultDescriptionDto.setCategory(CHARACTER.getCategory());
                resultDescriptionDto.setDescription("This number represents primarily your public and social qualities, but you would also exhibit much of the same in your personal relations, too.");
                break;
            case SOUL_URGE:
                resultDescriptionDto.setCategory(SOUL_URGE.getCategory());
                resultDescriptionDto.setDescription("This influence presents a view of your intuitive, soulful self.");
                break;
            case HIDDEN_AGENDA:
                resultDescriptionDto.setCategory(HIDDEN_AGENDA.getCategory());
                resultDescriptionDto.setDescription("This vibration offers your innate (hidden) desires.");
                break;
            case DIVINE_PURPOSE:
                resultDescriptionDto.setCategory(DIVINE_PURPOSE.getCategory());
                resultDescriptionDto.setDescription("This is your farewell-performance number vibration. It is what your Soulful Self has chosen to achieve in your latter years of life.");
                break;
            case PERSONAL_YEAR:
                resultDescriptionDto.setCategory(PERSONAL_YEAR.getCategory());
                resultDescriptionDto.setDescription("This is about you should expect about the current year.");
                break;
            case LOVE_COMPATIBILITY:
                resultDescriptionDto.setCategory(LOVE_COMPATIBILITY.getCategory());
                resultDescriptionDto.setDescription("This used to determine your love compatibility with another person.");
                break;
            default:
                throw new IllegalArgumentException("The category: " + category + " is not found");
        }
        return resultDescriptionDto;
    }

    public NumerologyComputer computerFactory(NumerologyValue.Category category) {
        NumerologyComputer computer;
        switch (category) {
            case DESTINY:
                computer = new Destiny();
                break;
            case PERSONALITY:
                computer = new Personality();
                break;
            case ATTITUDE:
                computer = new Attitude();
                break;
            case CHARACTER:
                computer = new com.javathinked.application.numerology.service.core.Character();
                break;
            case SOUL_URGE:
                computer = new SoulUrge();
                break;
            case HIDDEN_AGENDA:
                computer = new HiddenAgenda();
                break;
            case DIVINE_PURPOSE:
                computer = new DivinePurpose(new Destiny(), new Character());
                break;
            case PERSONAL_YEAR:
                computer = new PersonalYear();
                break;
            case LOVE_COMPATIBILITY:
                computer = new LoveCompatibility();
                break;
            default:
                throw new IllegalArgumentException(String.format("No computer available for the category %s", category.getDescription()));
        }
        return computer;
    }

    public List<ResultDescription> getSampleResultDescriptions() {
        var resultDescriptions = new ArrayList<ResultDescription>();
        resultDescriptions.add(TestData.sampleResultDescriptionOf(DESTINY));
        resultDescriptions.add(TestData.sampleResultDescriptionOf(PERSONALITY));
        resultDescriptions.add(TestData.sampleResultDescriptionOf(ATTITUDE));
        return resultDescriptions;
    }
}
