package com.vti.service;

import java.util.List;

import com.vti.entity.Account;
import com.vti.repository.AccountRepositoryV2;

public class AccountService implements IAccountService{
	private AccountRepositoryV2 acRepository;
	
	public AccountService() {
		acRepository = new AccountRepositoryV2();
	}
	
	@Override
	public List<Account> getListAccount() {
		return acRepository.getListAccount();
	}
	
}
