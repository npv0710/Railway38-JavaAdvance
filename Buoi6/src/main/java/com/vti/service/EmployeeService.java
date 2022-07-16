package com.vti.service;

import java.util.List;

import com.vti.entity.RegularEmployee;
import com.vti.repository.EmployeeRepository;
import com.vti.repository.IEmployeeRepository;

public class EmployeeService implements IEmployeeService {
	private IEmployeeRepository epRepository;
	
	public EmployeeService() {
		epRepository = new EmployeeRepository();
	}
	
	@Override
	public List<RegularEmployee> getListRegularEmployeees() {
		return epRepository.getListRegularEmployeees();
	}

	@Override
	public List<RegularEmployee> getListRegularEmployeeesSearchByName(String name) {
		return epRepository.getListRegularEmployeeesSearchByName(name);
	}

}
