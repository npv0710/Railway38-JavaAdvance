package com.vti.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entity.Account;
import com.vti.service.AccountService;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/accounts")
public class AccountController {
	private IAccountService acService;
	
	public AccountController() {
		acService = new AccountService();
	}
	
	@GetMapping
	public List<Account> getListAccounts() {
		return acService.getListAccount();
	}
	
}
