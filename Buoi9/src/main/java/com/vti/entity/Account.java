package com.vti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import lombok.Data;

@Entity
@Table(name = "Account")
@Data
@DynamicInsert
public class Account {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username", length = 50, nullable = false)
	private String username;
	
	@Column(name = "password", length = 150, nullable = false)
	private String password;
	
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	//@Column(name = "role", columnDefinition = "ENUM('ADMIN', 'MANAGER', 'EMPLOYEE') DEFAULT 'EMPLOYEE'") -- Set default value
	@Column(name = "role", columnDefinition = "ENUM('ADMIN', 'MANAGER', 'EMPLOYEE')")
	@Enumerated(EnumType.STRING)
	private AccountRole role;
	
	public enum AccountRole {
		ADMIN, MANAGER, EMPLOYEE
	}
	
	@Column(name = "status", columnDefinition = "SMALLINT DEFAULT 0")
	@Enumerated(EnumType.ORDINAL)
	private AccountStatus status;
	
	@ManyToOne
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	@LazyToOne(value = LazyToOneOption.NO_PROXY)
	private Department department;
	
	public enum AccountStatus {
		NOT_ACTIVE, ACTIVE
	}
	
	@Override
	public String toString() {
		return "[id = " + id + "; username = " + username + 
				"; firstName = " + firstName + "; lastName = " + lastName + 
				"; email = " + email + "; role = " + role + "; status = " + status +
				"deapartment = " + department + "]";
	}

}
