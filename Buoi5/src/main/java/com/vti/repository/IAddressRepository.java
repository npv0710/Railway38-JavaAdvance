package com.vti.repository;


import java.util.List;

import com.vti.entity.Account;
import com.vti.entity.Address;


public interface IAddressRepository {
	public List<Address> getListAddresses();
	Account getAccountById(int id);
}
