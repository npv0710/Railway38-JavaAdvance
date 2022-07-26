package com.vti.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.vti.service.IAccountService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private IAccountService acService;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(acService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
//	@Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/v2/api-docs",
//                                   "/configuration/ui",
//                                   "/swagger-resources/**",
//                                   "/configuration/security",
//                                   "/swagger-ui.html",
//                                   "/webjars/**");
//    }
//	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
        						   "/v2/api-docs",
                                   "/swagger-resources/**",
                                   "/swagger-ui.html",
                                   "/webjars/**");
    }
	
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
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
		corsConfiguration.applyPermitDefaultValues();
		
		UrlBasedCorsConfigurationSource urlSource = new UrlBasedCorsConfigurationSource();
		urlSource.registerCorsConfiguration("/**", corsConfiguration);
		return urlSource;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
