package com.javathinked.application.numerology.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javathinked.application.numerology.util.MapperWrapper;
import org.assertj.core.api.WithAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public abstract class BaseControllerTest implements WithAssertions {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected MapperWrapper mapper;
}
