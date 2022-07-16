package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entity.Department;
import com.vti.repository.IDepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService{
	@Autowired
	private IDepartmentRepository dpRepository;
	
	@Override
	public List<Department> getListDepartments() {
		return dpRepository.findAll();
	}

	@Override
	public void deleteDepartment(int id) {
		dpRepository.deleteById(id);
	}
	
}
