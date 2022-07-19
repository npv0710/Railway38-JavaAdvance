package com.vti.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vti.dto.DepartmentDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Department.DepartmentType;
import com.vti.form.AccountForm;
import com.vti.form.DepartmentForm;
import com.vti.service.IDepartmentService;

@RestController
@RequestMapping(value = "api/departments")
@Validated
public class DepartmentController {
	@Autowired
	private IDepartmentService dpService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<DepartmentDTO> getListDepartments() {
		
		List<Department> departments = dpService.getListDepartments();
		
		System.out.println(departments.get(0));
		
		List<DepartmentDTO> listDpDTO = modelMapper.map(
			departments,  
			new TypeToken <List<DepartmentDTO>> () {}.getType()
		);
		
		for (DepartmentDTO dpDTO : listDpDTO) {
			dpDTO.setType(DepartmentType.toEnum(dpDTO.getType()).toString());
		}
		return listDpDTO;
	}
	
	@PostMapping
	public ResponseEntity<?> createDepartment(@RequestBody @Valid DepartmentForm dpForm) {
		System.out.println("dp from received from client: ");
		System.out.println(dpForm);
		
		System.out.println(dpService.existsByName(dpForm.getName()));
		
		Department dp = modelMapper.map(dpForm, Department.class);
		
		//System.out.println(dp);
		
		dpService.createDepartment(dp);
		
		JSONObject message = new JSONObject();
		message.put("Message", "Create department successfully");
		
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDepartment(@PathVariable (name = "id") int id) {
		
		dpService.deleteDepartment(id);
		
		JSONObject message = new JSONObject();
		message.put("Message", "Department has just deleted");
		
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
}
