package com.vti.controller;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
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
		
		List<DepartmentDTO> listDpDTO = modelMapper.map(
			departments,  
			new TypeToken <List<DepartmentDTO>> () {}.getType()
		);
		
		return listDpDTO;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteDepartments(@PathVariable(name = "id") int id) {
		
		System.out.println("Id for deleting: " + id);
		
		dpService.deleteDepartment(id);
		
		return ResponseEntity.ok().body("The department deleted successfully");
	}
	
	
}
