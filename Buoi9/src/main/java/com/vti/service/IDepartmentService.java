package com.vti.service;

import java.util.List;

import com.vti.entity.Department;

public interface IDepartmentService {

	List<Department> getListDepartments();

	void deleteDepartment(int id);

}
