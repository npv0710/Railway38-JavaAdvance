package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping
	public List<AccountDTO> getListAccounts() {
		List<Account> accounts = acService.getListAccount();
		
		List<AccountDTO> listAccountDTO = new ArrayList();
		
		for (Account ac : accounts) {
			AccountDTO acDTO = new AccountDTO(
					ac.getId(), 
					ac.getUsername(), 
					//"", 
					ac.getFirstName(), 
					ac.getLastName(), 
					ac.getRole().toString(),
					"123abc"
					//ac.getAddresses().toString()
			);
			listAccountDTO.add(acDTO);
		}
		
		return listAccountDTO;
	}
	
	@SuppressWarnings("unused")
	@GetMapping("/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable(name = "id") int id) {
		Account ac = acService.getAccountById(id);
		AccountDTO acDTO = new AccountDTO(
				ac.getId(), 
				ac.getUsername(), 
				//"", 
				ac.getFirstName(), 
				ac.getLastName(), 
				ac.getRole().toString(),
				//"123abc"
				ac.getAddresses().toString()
		);
		if (ac != null) {
			return ResponseEntity.status(HttpStatus.OK).body(acDTO);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Found");
		}
	}
	
//	@SuppressWarnings("unused")
//	@GetMapping("/username/{username}")
//	public ResponseEntity<?> getAccountByUserName(@PathVariable(name = "username") String username) {
//		Account ac = acService.getAccountByUsername(username);
//		AccountDTO acDTO = new AccountDTO(
//				ac.getId(), 
//				ac.getUsername(), 
//				//"", 
//				ac.getFirstName(), 
//				ac.getLastName(), 
//				ac.getRole().toString(),
//				//"123abc"
//				ac.getAddresses().toString()
//		);
//		if (ac != null) {
//			return ResponseEntity.status(HttpStatus.OK).body(acDTO);
//		}else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Found");
//		}
//		
//	}
	
	
	
}
