package com.javathinked.application.numerology.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javathinked.application.numerology.configuration.ApiKeyProperties;
import com.javathinked.application.numerology.configuration.ApiSecurityConfiguration;
import com.javathinked.application.numerology.data.TestData;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class BaseIntegrationTest implements WithAssertions {

    @LocalServerPort
    protected String LOCAL_PORT;

    protected String url;

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    protected ApiKeyProperties apiKeyProperties;

    @BeforeEach
    void setUp() {
        url = String.format("http://localhost:%s", LOCAL_PORT) + TestData.API_V1_BASE_URL;
    }
}
