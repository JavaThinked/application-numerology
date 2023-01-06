package com.javathinked.application.numerology.configuration;

import com.javathinked.application.numerology.service.core.Character;
import com.javathinked.application.numerology.service.core.*;
import com.javathinked.application.numerology.util.MapperWrapper;
import com.javathinked.application.numerology.util.ModelMapperWrapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestApplicationConfiguration {

    @Bean
    public MapperWrapper createModelMapperWrapper() {
        return new ModelMapperWrapper();
    }

    @Bean
    public NumerologyComputer createDestiny() {
        return new Destiny();
    }

    @Bean
    public NumerologyComputer createPersonality() {
        return new Personality();
    }

    @Bean
    public NumerologyComputer createAttitude() {
        return new Attitude();
    }

    @Bean
    public NumerologyComputer createCharacter() {
        return new Character();
    }

    @Bean
    public NumerologyComputer createSoulUrge() {
        return new SoulUrge();
    }

    @Bean
    public NumerologyComputer createHiddenAgenda() {
        return new HiddenAgenda();
    }

    @Bean
    public NumerologyComputer createDivinePurpose(Destiny destiny, Character character) {
        return new DivinePurpose(destiny, character);
    }

    @Bean
    public NumerologyComputer createPersonalYear() {
        return new PersonalYear();
    }

    @Bean
    public NumerologyComputer createLoveCompatibility() {
        return new LoveCompatibility();
    }

    @Bean
    public ApiKeyProperties createApiKeyProperties() {
        return new ApiKeyProperties();
    }
}
