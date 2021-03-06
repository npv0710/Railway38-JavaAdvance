package com.vti.controller;

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
import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.form.DepartmentForm;
import com.vti.service.IDepartmentService;

@RestController
@RequestMapping(value = "api/departments")
public class DepartmentController {
	@Autowired
	private IDepartmentService dpService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<DepartmentDTO> getListDepartments() {
		
		List<Department> departments = dpService.getListDepartments();
		
		//System.out.println(departments.get(1));
		
		List<DepartmentDTO> listDpDTO = modelMapper.map(
			departments,  
			new TypeToken <List<DepartmentDTO>> () {}.getType()
		);
		
		return listDpDTO;
	}
	
	@PostMapping
	public ResponseEntity<?> createDepartment(@RequestBody DepartmentForm dpForm) {
		System.out.println("dp from received from client: ");
		System.out.println(dpForm);
		
		Department dp = modelMapper.map(dpForm, Department.class);
		
		System.out.println(dp);
		
		dpService.createDepartment(dp);
		
		JSONObject message = new JSONObject();
		message.put("Message", "Create department successfully");
		
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
}
