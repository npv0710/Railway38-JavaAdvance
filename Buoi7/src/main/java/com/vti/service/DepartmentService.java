package com.vti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService{
	@Autowired
	private IDepartmentRepository dpRepository;
	
	@Autowired
	private IAccountRepository acRepository;
	
	@Override
	public List<Department> getListDepartments() {
		return dpRepository.findAll();
	}
	@Override
	@Transactional
	public void createDepartment(Department dp) {
		System.out.println(dp.getAccounts().toString());
		
		List<Account> accounts = new ArrayList<>();
		
		for (Account ac : dp.getAccounts()) {//dp.getAccounts() return list accounts have only id
			
			Optional<Account> ac2 = acRepository.findById(ac.getId());//Get account details by id
			
			ac2.ifPresent(acObject -> accounts.add(acObject));
		}
		
		if (accounts.size() == 0) {
			Department department  = dpRepository.save(dp);
		}else {
			dp.setAccounts(accounts);//Add list accounts details for department
			Department department  = dpRepository.save(dp);
			for(Account account : accounts) {
				account.setDepartment(department);
			}
			acRepository.saveAll(accounts);
		}
	}
}
