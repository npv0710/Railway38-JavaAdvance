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
	private String stree;
	
	@Column(name = "city", length = 50, nullable = false)
	private String city;
	
//	@OneToOne(mappedBy = "address")
//	private Account account;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	
//	@ManyToMany(mappedBy = "addresses")
//	private List<Account> accounts;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStree() {
		return stree;
	}

	public void setStree(String stree) {
		this.stree = stree;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}
