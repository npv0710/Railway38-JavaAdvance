package com.vti.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.dto.JwtResponseDTO;
import com.vti.entity.Account;
import com.vti.exception.ErrorResponse;
import com.vti.form.AccountSigninForm;
import com.vti.form.AccountSignupForm;
import com.vti.service.IAccountService;
import com.vti.utils.JwtUtils;

@RestController
@RequestMapping(value = "api/auth")
@Validated
public class AuthController {
	@Autowired
	private IAccountService acService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid AccountSignupForm acSignupForm) {
		
		System.out.println(acSignupForm);
		
		Account ac = modelMapper.map(acSignupForm, Account.class);
		
		acService.createAccount(ac);
		
		return ResponseEntity.status(HttpStatus.OK).body("Create account successfully");
	}
	
//	@PostMapping("/signin")
//	public ResponseEntity<?> signin(Principal principal) {
//
//		Account ac = acService.findByUsername(principal.getName());
//		
//		AccountDTO acDTO = modelMapper.map(ac, AccountDTO.class);
//		
//		return ResponseEntity.status(HttpStatus.OK).body(acDTO);
//	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody AccountSigninForm acSgnForm) {
		System.out.println(acSgnForm);
		
		
		Account ac = acService.findByUsername(acSgnForm.getUsername());
		
		if (ac == null) throw new UsernameNotFoundException(acSgnForm.getUsername());
		
		try {
			Authentication auth = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(acSgnForm.getUsername(), acSgnForm.getPassword())
			);
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			
			String token = jwtUtils.generateToken(userDetails.getUsername());
			
			System.out.println(userDetails.getUsername());
			System.out.println(userDetails.getAuthorities().toString());
			
			return ResponseEntity.status(HttpStatus.OK).body(
					new JwtResponseDTO(token, userDetails.getUsername(), userDetails.getAuthorities().toString())
			);
		}catch (Exception ex) {
			System.out.println("Ex on the controller: ");
			System.out.println(ex);
			ErrorResponse errRp = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Password invalid");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errRp);
		}
	}
}
