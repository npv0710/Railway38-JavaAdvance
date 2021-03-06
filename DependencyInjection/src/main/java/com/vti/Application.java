package com.vti;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vti.controller.DepartmentController;
import com.vti.dto.DepartmentDTO;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		System.out.println("Dependency Injection");
		
		DIContainer dIContainer = new DIContainer();
		
		DepartmentController dpController = dIContainer.initDpController();
		
		List<DepartmentDTO> listDpDTO = dpController.getListDepartments();
		
		for (DepartmentDTO dpDTO : listDpDTO) {
			System.out.println(dpDTO.toString());
		}
				
		SpringApplication.run(Application.class, args);
	}
}
