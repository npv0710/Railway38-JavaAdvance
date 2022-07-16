package com.vti.repository;

import java.util.List;

import com.vti.entity.Department;

public interface IDepartmentRepository{

	List<Department> getListDepartments();

	int createDepartment(Department dp);

}
