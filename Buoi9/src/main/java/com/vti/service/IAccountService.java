package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Account;

public interface IAccountService {

	List<Account> getListAccount();

	Account getAccountById(int id);

	Account getAccountByUsername(String username);

	Page<Account> getListAccountsPaging(Pageable pageable);

	Page<Account> getListAccountsPaging(Pageable pageable, String nameSearching);

}
