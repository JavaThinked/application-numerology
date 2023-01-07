package com.javathinked.application.numerology.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfiguration {

    private final ApiKeyProperties apiKeyProperties;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public ApiSecurityConfiguration(ApiKeyProperties apiKeyProperties, @Qualifier("apiAuthenticationEntryPoint") AuthenticationEntryPoint authenticationEntryPoint) {
        this.apiKeyProperties = apiKeyProperties;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        var filter = new ApiKeyAuthenticationFilter(apiKeyProperties.getHeaderName());
        filter.setAuthenticationManager(authentication -> {
            var principal = (String) authentication.getPrincipal();
            if (apiKeyProperties.getApiKey().equals(principal)) {
                authentication.setAuthenticated(true);
            }
            return authentication;
        });
        security.antMatcher("/**")
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
        return security.build();
    }
}
