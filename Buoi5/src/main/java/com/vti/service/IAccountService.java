package com.vti.service;

import java.util.List;

import com.vti.entity.Account;

public interface IAccountService {
	
	List<Account> getListAccount();
	
	Account getAccountById(int id);
	
	Account getAccountByUsername(String username);
	
}
