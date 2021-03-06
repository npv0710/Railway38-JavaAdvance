package com.vti.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NonNull;

@Data
public class DepartmentDTO {
	private int id;
	
	private String name;
	
	private int totalMember;
	
	private String type;
	
	@JsonFormat(pattern = "dd-MM-yyy HH:mm:ss")
	private Date createdAt;
	
	private List<AcDTO> accounts;
	
	@Data
	public static class AcDTO {
		private int id;
		
		private String username;
		
		private String firstName;
		
		private String lastName;
		
		private String email;
		
		private String role;
		
		private String status;
	}
}
