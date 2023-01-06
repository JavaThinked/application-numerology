package com.javathinked.application.numerology.configuration;

import com.javathinked.application.numerology.controller.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfiguration {

    private final ApiKeyProperties apiKeyProperties;

    @Autowired
    public ApiSecurityConfiguration(ApiKeyProperties apiKeyProperties) {
        this.apiKeyProperties = apiKeyProperties;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        var filter = new ApiKeyAuthenticationFilter(apiKeyProperties.getHeaderName());
        filter.setAuthenticationManager(authentication -> {
            var principal = (String) authentication.getPrincipal();
            if (!apiKeyProperties.getApiKey().equals(principal)) {
                throw new UnauthorizedException("The API key was not found or not the expected value");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });
        security.antMatcher("/v1/numerology/**")
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(filter)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
        return security.build();
    }
}
