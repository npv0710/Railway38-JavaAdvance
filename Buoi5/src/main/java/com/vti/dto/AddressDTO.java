package com.vti.dto;


public class AddressDTO {
	private int id;
	
	private String street;
	
	private String city;
	
	//private String fullName;
	private String accounts;

	public AddressDTO(int id, String street, String city, String accounts) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.accounts = accounts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	};
	
	
}
