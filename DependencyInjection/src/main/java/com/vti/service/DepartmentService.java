package com.vti.service;

import java.util.List;

import javax.transaction.Transactional;

import com.vti.entity.Department;
import com.vti.repository.AccountRepositoryV2;
import com.vti.repository.DepartmentRepository;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;

public class DepartmentService implements IDepartmentService{
//	private DepartmentRepository dpRepository;
//	
//	private AccountRepositoryV2 acRepository;
	
	private IDepartmentRepository dpRepository;
	
	private IAccountRepository acRepository;
	
//	public DepartmentService() {
//		dpRepository = new DepartmentRepository();
//		acRepository = new AccountRepositoryV2();
//	}
	
	public DepartmentService(IDepartmentRepository dpRepository, IAccountRepository acRepository) {
		this.dpRepository = dpRepository;
		this.acRepository = acRepository;
	}
	
	@Override
	public List<Department> getListDepartments() {
		return dpRepository.getListDepartments();
	}
	
	@Override
	@Transactional
	public int createDepartment(Department dp) {
		int dpId = dpRepository.createDepartment(dp);
		
		for (int i = 0; i < dp.getAccounts().size(); i ++) {
			dp.getAccounts().get(i).setDepartment(dp);
			//acRepository.createAccount(dp.getAccounts().get(i));
		}
		
		return dpId;
	}

	@Override
	public void deleteDepartment(int id) {
		// TODO Auto-generated method stub
		
	}
}
