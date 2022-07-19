package com.vti.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.entity.Account;
import com.vti.exception.ErrorResponse;
import com.vti.repository.IAccountRepository;
import com.vti.service.IAccountService;

public class AuthTokenFilter extends OncePerRequestFilter{
	@Autowired
	private IAccountService acService;
	
	@Autowired
	private IAccountRepository acRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String body = request.getReader().lines()
				    .reduce("", (accumulator, actual) -> accumulator + actual);
			System.out.println(request.getReader().lines().toList());
			System.out.println(body instanceof Object);
			
			//Account ac = acRepository.getAccountByUsername(username);
			
			//throw new UsernameNotFoundException("123abcd");
			
			filterChain.doFilter(request, response);
		}catch (Exception e) {
			System.out.println("runtime ex: ");
			System.out.println(e);
			
			throw e;
			
//			String msg = "";
//			msg = e.getMessage();
//			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, msg);
//			
//			response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.getWriter().write(convertObjectToJson(errorResponse));
		}
	}
	
	public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}
