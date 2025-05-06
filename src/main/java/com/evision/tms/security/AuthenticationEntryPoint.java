package com.evision.tms.security;

import com.evision.tms.constants.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

@Component
@Slf4j
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint, Serializable {

    @Serial
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(final HttpServletRequest request, HttpServletResponse response,
                         final AuthenticationException authException) throws IOException {
        log.info("Inside Class: AuthenticationEntryPoint , Method: commence ");
        response.sendError
                (HttpServletResponse.SC_UNAUTHORIZED, SecurityConstants.UNAUTHORIZED);
    }
}