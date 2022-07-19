package com.vti.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vti.service.IAccountService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private IAccountService acService;
	
	protected void configure(AuthenticationManagerBuilder auth){
		try {
			auth.userDetailsService(acService).passwordEncoder(new BCryptPasswordEncoder());
		}catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
//	@Bean
//	public AuthTokenFilter createAuthTokenFiler() {
//		return new AuthTokenFilter();
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.authorizeRequests()
			.antMatchers("/api/accounts/**").hasAnyAuthority("ADMIN", "MANAGER")
			//.antMatchers("/api/accounts/**").permitAll()
			.antMatchers("/api/departments/**").hasAuthority("ADMIN")
			.antMatchers("/api/auth/**").permitAll()
			.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(authEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
		
		//http.addFilterBefore(createAuthTokenFiler(), UsernamePasswordAuthenticationFilter.class);
	}
}
