package com.vti.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountSignupForm;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/auth")
@Validated
public class AuthController {
	@Autowired
	private IAccountService acService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid AccountSignupForm acSignupForm) {
		
		System.out.println(acSignupForm);
		
		Account ac = modelMapper.map(acSignupForm, Account.class);
		
		acService.createAccount(ac);
		
		return ResponseEntity.status(HttpStatus.OK).body("Create account successfully");
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(Principal principal) {

		Account ac = acService.findByUsername(principal.getName());
		
		AccountDTO acDTO = modelMapper.map(ac, AccountDTO.class);
		
		return ResponseEntity.status(HttpStatus.OK).body(acDTO);
	}
	
	
}
