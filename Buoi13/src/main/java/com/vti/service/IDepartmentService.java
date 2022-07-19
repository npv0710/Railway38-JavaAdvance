package com.vti.service;

import java.util.List;

import com.vti.entity.Department;

public interface IDepartmentService {

	List<Department> getListDepartments();

	void createDepartment(Department dp);
	
	boolean existsByName(String name);

	void deleteDepartment(int id);

}
