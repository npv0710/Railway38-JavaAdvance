package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.dto.DepartmentDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.service.DepartmentService;
import com.vti.service.IDepartmentService;

@RestController
@RequestMapping(value = "api/departments")
public class DepartmentController {
	private IDepartmentService dpService;
	private ModelMapper modelMapper;
	
//	public DepartmentController() {
//		dpService = new DepartmentService();
//		modelMapper = new ModelMapper();
//	}
	
	public DepartmentController(IDepartmentService dpService, ModelMapper modelMapper) {
		this.dpService = dpService;
		this.modelMapper = modelMapper;
	}
	
	
	@GetMapping
	public List<DepartmentDTO> getListDepartments() {
		List<Department> departments = dpService.getListDepartments();
		
		List<DepartmentDTO> listDpDTO = modelMapper.map(departments, 
				new TypeToken<List<DepartmentDTO>>() {}.getType()
		);
		
		return listDpDTO;
	}
	
	
	@PostMapping
	public ResponseEntity<?> createDepartment(@RequestBody DepartmentDTO dpDTO) {
		System.out.println(dpDTO);
		System.out.println(dpDTO.getAccounts().get(0).getId());
		
		Department dp = modelMapper.map(dpDTO, Department.class);
		
		dpService.createDepartment(dp);
		
		System.out.println(dp);
		
		JSONObject message = new JSONObject();
		message.put("Message", "Success");
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
}
