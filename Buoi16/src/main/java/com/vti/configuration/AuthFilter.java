package com.vti.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.exception.ErrorResponse;
import com.vti.service.IAccountService;
import com.vti.utils.JwtUtils;

import io.jsonwebtoken.JwtException;

public class AuthFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private IAccountService acService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Do fitler once per request: ");
		try {
			String token = getTokenFromRequest(request);
			if (token != null && jwtUtils.validateToken(token)) {
				String username = jwtUtils.getUsernameByToken(token);
				
				UserDetails userDetails = acService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(), 
					null,
					userDetails.getAuthorities()
				);
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
			filterChain.doFilter(request, response);
		}catch (Exception ex) {
			System.out.println("ex do filter: ");
			System.out.println(ex);
			if (ex instanceof JwtException) {
				ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
				response.setContentType("application/json");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write(convertObjectToJson(errorResponse));
			}else throw ex;
		}
	}
	
	public String convertObjectToJson(Object ob) throws JsonProcessingException {
		if (ob == null) return null;
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(ob);
	}
	
	@SuppressWarnings("unused")
	private String getTokenFromRequest(HttpServletRequest req) {
		String header = req.getHeader("Authorization");
		
		if (StringUtils.hasText(header) && header.startsWith("Bearer")) {
			return header.substring(7, header.length());
		}
		return null;
	}
}
