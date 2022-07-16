package com.vti.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entity.Account;
import com.vti.repository.IAccountRepository;

@Service
public class AccountService implements IAccountService{
	@Autowired
	private IAccountRepository acRepository;
	
	@Override
	public List<Account> getListAccount() {
		return acRepository.findAll();
	}

	@Override
	public Account getAccountById(int id) {
		//C1 sử dụng optionl
		//Optional<Account> opAc = acRepository.findById(id);
		//return opAc.get();
		
		//C2 override getById data jpa
		Account ac = acRepository.findById(id);
		return ac;
	}

	@Override
	public Account getAccountByUsername(String username) {
		return acRepository.getAccountByUsername(username);
	}
	
}
