package com.vti.repository;

import java.util.List;

import com.vti.entity.RegularEmployee;

public interface IEmployeeRepository {
	
	List<RegularEmployee> getListRegularEmployeees();

	List<RegularEmployee> getListRegularEmployeeesSearchByName(String name);
	
}
