package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.service.AccountService;

@RestController
@RequestMapping(value = "api/accounts")
public class AccountController {
	
	private AccountService acService;
	
	private ModelMapper modelMapper;
	
	public AccountController () {
		acService = new AccountService();
		modelMapper = new ModelMapper();
	}
	
	@GetMapping
	public List<AccountDTO> getListAccounts() {
		
		List<Account> accounts = acService.getListAccount();
		
		List<AccountDTO> listAcDTO = modelMapper.map(accounts, 
				new TypeToken<List<AccountDTO>>() {}.getType()
		);
		
		return listAcDTO;
	}
	
	
	
}
