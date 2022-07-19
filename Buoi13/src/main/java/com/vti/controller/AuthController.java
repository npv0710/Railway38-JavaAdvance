package com.vti.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountSigninForm;
import com.vti.form.AccountSignupForm;
import com.vti.repository.IAccountRepository;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/auth")
@Validated
public class AuthController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IAccountService acService;
	
	@Autowired
	private IAccountRepository acRepository;
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(Principal principal) throws UsernameNotFoundException{
		Account ac = acRepository.findByUsername(principal.getName());
		
		AccountDTO acDTO = modelMapper.map(ac, AccountDTO.class);
		
		return ResponseEntity.status(HttpStatus.OK).body(ac);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid AccountSignupForm acSignupForm) {
		System.out.println("AccountSignupForm received from client: ");
		System.out.println(acSignupForm);
		
		Account ac = modelMapper.map(acSignupForm, Account.class);
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String enCryptPassword = bCryptPasswordEncoder.encode(acSignupForm.getPassword());
		ac.setPassword(enCryptPassword);
		
		acService.createAccount(ac);
		
		JSONObject message = new JSONObject();
		message.put("status", 200);
		message.put("resultText", "Register account successfully!");
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
}
