package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/accounts")
public class AccountController {
	
	@Autowired
	private IAccountService acService;
	
	@GetMapping
	public List<AccountDTO> getListAccounts() {
		
		List<Account> accounts = acService.getListAccount();
		
		List<AccountDTO> listAcDTO = new ArrayList();
		
		for (Account ac: accounts) {
			AccountDTO acDTO = new AccountDTO(
				ac.getId(), 
				ac.getUsername(), 
				ac.getFirstName(), 
				ac.getLastName(), 
				ac.getEmail(), 
				ac.getRole().toString(), 
				ac.getStatus().toString(), 
				ac.getDepartment().getName()
			);
			listAcDTO.add(acDTO);
		}
		
		System.out.println(accounts.get(0).getUsername());
		
		return listAcDTO;
	}
	
	
	
}
