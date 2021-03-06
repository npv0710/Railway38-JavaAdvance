package com.vti.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

import com.vti.converter.DepartmentConverter;

@Entity
@Table(name = "Department")
public class Department {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "total_member")
	private int totalMember;
	
//	@Column(name = "type", columnDefinition = "ENUM('DEV', 'TEST', 'SCRUM_MASTER', 'PM')")
//	@Enumerated(EnumType.STRING)
//	private DepartmentType type;
	
	@Column(name = "`type`")
	@Convert(converter = DepartmentConverter.class)
	private DepartmentType type;
	
	@Column(
		name = "created_at", 
		nullable = false,
	    columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"
	)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;
	
	@OneToMany(mappedBy = "department")
	//@Cascade(CascadeType.ALL)
	private List<Account> accounts;
	
	
	public enum DepartmentType {
		DEV("D"), TEST("T"), SCRUM_MASTER("S"), PM("P");
		
		private String type;
		
		DepartmentType(String type) {
			this.type = type;
		}
		
		public String getType() {
			return type;
		}
		
		public static DepartmentType toEnum(String type) {
			for (DepartmentType item : DepartmentType.values()) {
				//System.out.println(item);
				if (item.getType().equals(type)) return item;
			}
			return null;
		}
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getTotalMember() {
		return totalMember;
	}


	public void setTotalMember(int totalMember) {
		this.totalMember = totalMember;
	}


	public DepartmentType getType() {
		return type;
	}


	public void setType(DepartmentType type) {
		this.type = type;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public List<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
}
