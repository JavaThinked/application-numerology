package com.javathinked.application.numerology.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperWrapper implements MapperWrapper {

    private final ModelMapper mapper;

    public ModelMapperWrapper() {
        this.mapper = new ModelMapper();
    }

    @Override
    public <T> T performMapping(Object source, Class<T> destination) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(source, destination);
    }
}
