package com.vti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
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
		Department deparment = dpRepository.save(dp);
		
		System.out.println(deparment);
		
		List<Account> accounts = new ArrayList<>();
		
		for (Account ac : deparment.getAccounts()) {
			
			Account acTemp = acRepository.findById(ac.getId());
			
			accounts.add(acTemp);
		}
		
		for (Account ac : accounts) {
			
			ac.setDepartment(deparment);
		}
		
		acRepository.saveAll(accounts);
	}

	@Override
	public boolean existsByName(String name) {
//		ExampleMatcher NAME_MATCHER = ExampleMatcher.matching()
//		.withMatcher("name", GenericPropertyMatchers.ignoreCase());
//        Example<Department> example = Example.<Department>of(new Department(name), NAME_MATCHER);
//        boolean exists = dpRepository.exists(example);
		return dpRepository.existsByName(name);
	}

	@Override
	public void deleteDepartment(int id) {
		dpRepository.deleteById(id);
	}
	
}
