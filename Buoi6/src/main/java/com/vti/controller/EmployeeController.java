package com.vti.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entity.RegularEmployee;
import com.vti.service.EmployeeService;
import com.vti.service.IEmployeeService;

@RestController
@RequestMapping(value = "api/employeees")
public class EmployeeController {
	private IEmployeeService epService;
	
	public EmployeeController() {
		epService = new EmployeeService();
	}
	
	@GetMapping("/regular")
	public List<RegularEmployee> getListRegularEmployeees() {
		return epService.getListRegularEmployeees();
	}
	
	
	@GetMapping("/regular/name")
	public List<RegularEmployee> getListRegularEmployeeesSearchByName(@RequestParam(name = "name") String name) {
		System.out.println("name query: " + name);
		
		return epService.getListRegularEmployeeesSearchByName(name);
	}
	
}
