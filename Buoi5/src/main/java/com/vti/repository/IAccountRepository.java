package com.vti.repository;


import java.util.List;

import com.vti.entity.Account;

public interface IAccountRepository {
	
	public static final String JDK_VERSION = "JDK_16";//Demo
	
	public List<Account> getListAccounts();
	
	Account getAccountById(int id);
	
	Account getAccountByUsername(String username);
}
