package com.vti.dto;

public class AccountDTO {
	
	private int id;

	private String username;
	
	//private String password;
	
	private String firstName;
	
	public AccountDTO(
			int id, 
			String username, 
			//String password, 
			String firstName, 
			String lastName, 
			String role,
			String addresses
	) {
		super();
		this.id = id;
		this.username = username;
		//this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.addresses = addresses;
	}

	private String lastName;
	
	private String role;
	
	//private String address;
	
	private String addresses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddresses() {
		return addresses;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
	
}
