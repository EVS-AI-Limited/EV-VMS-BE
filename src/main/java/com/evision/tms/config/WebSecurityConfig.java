package com.evision.tms.config;

import com.evision.tms.filter.TokenFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Slf4j
@EnableWebSecurity
@Component
public class WebSecurityConfig {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    public AuthenticationManager authenticationManager
            (final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        log.info("Inside Class: WebSecurityConfig , Method: authenticationManager ");
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public SecurityFilterChain filterChain
            (final HttpSecurity httpSecurity) throws Exception {
        log.info("Inside Class: WebSecurityConfig , Method: filterChain ");
        httpSecurity.cors().and().csrf().disable().authorizeRequests().antMatchers("/authenticate")
                .permitAll().
                antMatchers("/v3/api-docs/**",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/core/cxml/**", //Permit for now until we have Long Live Token implementation for CXML Service APIs
                        "/favicon.ico",
                        "/webjars/**").permitAll().
                        anyRequest().authenticated().and().
                        exceptionHandling().
                authenticationEntryPoint(authenticationEntryPoint).and().
                sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic();

        httpSecurity.addFilterBefore
                (tokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
    public void setJwtRequestFilter(TokenFilter jwtRequestFilter) {
        this.tokenFilter = jwtRequestFilter;
    }

}
