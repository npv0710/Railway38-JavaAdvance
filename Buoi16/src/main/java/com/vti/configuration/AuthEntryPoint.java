package com.vti.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.exception.ErrorResponse;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println("auth exception: ");
		System.out.println(authException);
		
		response.setContentType("application/json");
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, "User name or password invalid!");
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(convertObjectToJson(errorResponse));
	}
	
	public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		System.out.println("Access denied");
		System.out.println(accessDeniedException);
		
		response.setContentType("application/json");
	    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	    
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN, "Access denied");
		
		response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(convertObjectToJson(errorResponse));
	}
}
