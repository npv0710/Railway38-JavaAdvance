package com.vti.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Convert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.converter.DepartmentConverter;

import lombok.Data;

@Data
public class DepartmentDTO {
	private int id;
	
	private String name;
	
	private int totalMember;
	
	@Convert(converter = DepartmentConverter.class)
	private String type;
	
	@JsonFormat(pattern = "dd-MM-yyy HH:mm:ss")
	private Date createdAt;
	
	private List<AcDTO> accounts;
	
//	public int getId() {
//		return id;
//	}
//
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//
//	public String getName() {
//		return name;
//	}
//
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//
//	public int getTotalMember() {
//		return totalMember;
//	}
//
//
//	public void setTotalMember(int totalMember) {
//		this.totalMember = totalMember;
//	}
//
//
//	public String getType() {
//		return type;
//	}
//
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
//
//
//	public List<AcDTO> getAccounts() {
//		return accounts;
//	}
//
//
//	public void setAccounts(List<AcDTO> accounts) {
//		this.accounts = accounts;
//	}
//	
//	
//	public DepartmentDTO(int id, String name, int totalMember, String type, Date createdAt) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.totalMember = totalMember;
//		this.type = type;
//		this.createdAt = createdAt;
//	}
//	
//	public DepartmentDTO(int id, String name, int totalMember, String type, Date createdAt, List<AcDTO> accounts) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.totalMember = totalMember;
//		this.type = type;
//		this.createdAt = createdAt;
//		this.accounts = accounts;
//	}

	@Data
	public static class AcDTO {
		private int id;
		
		private String username;
		
		private String firstName;
		
		private String lastName;
		
		private String email;
		
		private String role;
		
		private String status;

//		public int getId() {
//			return id;
//		}
//
//		public void setId(int id) {
//			this.id = id;
//		}
//
//		public String getUsername() {
//			return username;
//		}
//
//		public void setUsername(String username) {
//			this.username = username;
//		}
//
//		public String getFirstName() {
//			return firstName;
//		}
//
//		public void setFirstName(String firstName) {
//			this.firstName = firstName;
//		}
//
//		public String getLastName() {
//			return lastName;
//		}
//
//		public void setLastName(String lastName) {
//			this.lastName = lastName;
//		}
//
//		public String getEmail() {
//			return email;
//		}
//
//		public void setEmail(String email) {
//			this.email = email;
//		}
//
//		public String getRole() {
//			return role;
//		}
//
//		public void setRole(String role) {
//			this.role = role;
//		}
//
//		public String getStatus() {
//			return status;
//		}
//
//		public void setStatus(String status) {
//			this.status = status;
//		}
//
//		public AcDTO(int id, String username, String firstName, String lastName, String email, String role,
//				String status) {
//			super();
//			this.id = id;
//			this.username = username;
//			this.firstName = firstName;
//			this.lastName = lastName;
//			this.email = email;
//			this.role = role;
//			this.status = status;
//		}
		
		
	}
	
}
