package com.vti.service;

import java.util.List;

import com.vti.entity.Department;

public interface IDepartmentService {

	List<Department> getListDepartments();

	int createDepartment(Department dp);

	void deleteDepartment(int id);

}
