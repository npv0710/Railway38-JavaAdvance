package com.vti;

import org.modelmapper.ModelMapper;

import com.vti.controller.DepartmentController;
import com.vti.repository.AccountRepository;
import com.vti.repository.DepartmentRepository;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.service.DepartmentService;
import com.vti.service.IDepartmentService;

public class DIContainer {
	
	public IDepartmentService initDpService() {
		IDepartmentRepository dpRepository = new DepartmentRepository();
		IAccountRepository acRepository = new AccountRepository();
		
		return new DepartmentService(dpRepository, acRepository);
	}
	
	public DepartmentController initDpController() {
		ModelMapper modelMapper = new ModelMapper();
		return new DepartmentController(initDpService(), modelMapper);
	}
}
