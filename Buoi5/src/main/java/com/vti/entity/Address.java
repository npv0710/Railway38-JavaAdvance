package com.vti.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class Address {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "street", length = 50, nullable = false)
	private String street;
	
	@Column(name = "city", length = 50, nullable = false)
	private String city;
	
//	@OneToOne(mappedBy = "address")
//	private Account account;
	
	//@ManyToOne(fetch = FetchType.EAGER)
//	@ManyToOne
//	@JoinColumn(name = "account_id", referencedColumnName = "id")
//	private Account account;
	
	@ManyToMany(mappedBy = "addresses")
	private List<Account> accounts;
	
//	public Account getAccount() {
//		return account;
//	}
//
//	public void setAccount(Account account) {
//		this.account = account;
//	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString() {
		return "[id = " + id + "; street = " + street + "; city = " + city + "]";
	}
	
}
