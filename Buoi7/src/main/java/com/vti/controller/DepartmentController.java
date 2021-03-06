package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.form.DepartmentFilterForm;
import com.vti.service.IDepartmentService;

@RestController
@RequestMapping(value = "api/departments")
public class DepartmentController {
	@Autowired
	private IDepartmentService dpService;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	@GetMapping
//	public List<DepartmentDTO> getListDepartments() {
//		
//		List<Department> departments = dpService.getListDepartments();
//		
//		List<DepartmentDTO> listDpDTO = new ArrayList();
//		
//		List<DepartmentDTO.AcDTO> listAcDTO = new ArrayList();
//		
//		for (Department dp: departments) {
//			DepartmentDTO dpDTO = new DepartmentDTO(
//				dp.getId(), 
//				dp.getName(), 
//				dp.getTotalMember(), 
//				dp.getType().toString(), 
//				dp.getCreatedAt()
//			);
//			for (Account ac : dp.getAccounts()) {
//				DepartmentDTO.AcDTO acDTO = new DepartmentDTO.AcDTO(
//					ac.getId(),
//					ac.getUsername(),
//					ac.getFirstName(),
//					ac.getLastName(),
//					ac.getEmail(),
//					ac.getRole().toString(),
//					ac.getStatus().toString()
//				);
//				listAcDTO.add(acDTO);
//			}
//			dpDTO.setAccounts(listAcDTO);
//			
//			listDpDTO.add(dpDTO);
//		}
//		
//		return listDpDTO;
//	}
	@GetMapping
	public List<DepartmentDTO> getListDepartments() {
		List<Department> departments = dpService.getListDepartments();
		//System.out.println(departments.get(0).getType());
//		modelMapper.addMappings(new PropertyMap<DepartmentDTO, Department>() {
//            @Override
//            protected void configure() {
//                skip(destination.getType());
//            }
//        });
		
		List<DepartmentDTO> listDpDTO = new ArrayList<>();
		for (Department dp: departments) {
			DepartmentDTO dpDTO = modelMapper.map(dp, DepartmentDTO.class);
			dpDTO.setType(Department.DepartmentType.toEnum(dpDTO.getType()).toString());
			listDpDTO.add(dpDTO);
		}
		
//		List<DepartmentDTO> listDpDTO = modelMapper.map(departments, new TypeToken<List<DepartmentDTO>>() {}.getType());
//		
//		for (DepartmentDTO dp: listDpDTO) {
//			dp.setType(Department.DepartmentType.toEnum(dp.getType()).toString());
//		}
		
		return listDpDTO;
	}
	
	@PostMapping
	public ResponseEntity<?> createDepartment(@RequestBody DepartmentDTO dpDTO) {
		System.out.println(dpDTO);
		
		Department dp = modelMapper.map(dpDTO, Department.class);
		
		dpService.createDepartment(dp);
		
		System.out.println(dp);
		
		JSONObject message = new JSONObject();
		message.put("Message", "Success");
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
}
