package com.vti.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	
	@Value("${vti.app.jwtSecretKey}")
	private String JWT_SECRET_KEY;
	
	@Value("${vti.app.jwtExpirationMs}")
	private int JWT_EXPIRATION_MS;
	
	@SuppressWarnings("deprecation")
	public String generateToken(String username) {
		//UserDetails userDetails = (UserDetails) auth.getPrincipal();
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_MS))
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
				.compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token);
			return true;
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public String getUsernameByToken(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}
	
}
