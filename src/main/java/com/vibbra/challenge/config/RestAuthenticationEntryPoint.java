package com.vibbra.challenge.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vibbra.challenge.exceptionhandler.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        ApiError apiError = new ApiError(authException.getLocalizedMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(apiError));
    }
}
