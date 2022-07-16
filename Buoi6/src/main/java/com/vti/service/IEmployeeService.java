package com.vti.service;

import java.util.List;

import com.vti.entity.RegularEmployee;

public interface IEmployeeService {
	
	List<RegularEmployee> getListRegularEmployeees();

	List<RegularEmployee> getListRegularEmployeeesSearchByName(String name);
	
}
