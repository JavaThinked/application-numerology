package com.javathinked.application.numerology.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javathinked.application.numerology.configuration.ApiKeyProperties;
import com.javathinked.application.numerology.configuration.ApiSecurityConfiguration;
import com.javathinked.application.numerology.configuration.TestApplicationConfiguration;
import com.javathinked.application.numerology.util.MapperWrapper;
import org.assertj.core.api.WithAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@SpringJUnitWebConfig(TestApplicationConfiguration.class)
@Import(ApiSecurityConfiguration.class)
public abstract class BaseControllerTest implements WithAssertions {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected MapperWrapper mapper;
}
