package com.vti.controller;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.repository.IAccountRepository;

@RestController
@RequestMapping(value = "api/auth")
public class AuthController {
	@Autowired
	private IAccountRepository acRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/signin")
	public AccountDTO signin(Principal principal) {
		
		System.out.println("principal: " + principal.toString());
		
		String username = principal.getName();
		
		System.out.println("username: " + username);
		
		Account ac = acRepository.findByUsername(username);
		
		//System.out.println(ac.getDepartment().toString());
		
		AccountDTO acDTO = modelMapper.map(ac, AccountDTO.class);
		return acDTO;
		
	}
	
}
