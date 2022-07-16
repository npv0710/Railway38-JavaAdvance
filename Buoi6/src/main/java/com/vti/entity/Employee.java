package com.vti.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
//Single table
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
//

//Table per class
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//

//Joined
@Inheritance(strategy = InheritanceType.JOINED)
//
public class Employee {
	
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

	@Column(name = "id")
	@Id
	//Single table
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//
	//Table per class
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//
	
	//Joined
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//
	private int id;
	
	@Column(name = "`name`", length = 50, nullable = false)
	private String name;
	
}
