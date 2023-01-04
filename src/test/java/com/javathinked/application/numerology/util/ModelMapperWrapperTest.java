package com.javathinked.application.numerology.util;

import com.javathinked.application.numerology.configuration.TestApplicationConfig;
import com.javathinked.application.numerology.controller.dto.DummyModelDto;
import com.javathinked.application.numerology.service.model.DummyModel;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringJUnitWebConfig(TestApplicationConfig.class)
class ModelMapperWrapperTest implements WithAssertions {

    private final MapperWrapper mapperWrapper;

    @Autowired
    public ModelMapperWrapperTest(MapperWrapper mapperWrapper) {
        this.mapperWrapper = mapperWrapper;
    }

    @Test
    void givenModel_whenMapModelToDto_thenReturnsDto() {
        var model = new DummyModel();
        model.setId(1);
        model.setName("DUMMY_NAME");
        model.setAge(25);

        var modelDto = mapperWrapper.performMapping(model, DummyModelDto.class);

        assertAll(
                () -> assertThat(modelDto.getId()).isEqualTo(String.valueOf(model.getId())),
                () -> assertThat(modelDto.getName()).isEqualTo(model.getName()),
                () -> assertThat(modelDto.getAge()).isEqualTo(String.valueOf(model.getAge()))
        );
    }

    @Test
    void givenDto_whenMapDtoToModel_thenReturnsModel() {
        var modelDto = new DummyModelDto();
        modelDto.setId("1");
        modelDto.setName("DUMMY_NAME");
        modelDto.setAge("25");

        var model = mapperWrapper.performMapping(modelDto, DummyModel.class);

        assertAll(
                () -> assertThat(model.getId()).isEqualTo(Integer.valueOf(modelDto.getId())),
                () -> assertThat(model.getName()).isEqualTo(modelDto.getName()),
                () -> assertThat(model.getAge()).isEqualTo(Integer.valueOf(modelDto.getAge()))
        );
    }
}