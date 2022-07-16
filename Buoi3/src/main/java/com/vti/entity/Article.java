package com.vti.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="Article")
public class Article {
	
	public Article() {
		
	}
	
	public Article(String title, String type, Status status){
		this.title = title;
		this.type = type;
		this.status = status;
	}
	
//	@Column(name = "id")
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int id;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type = "uuid-char")
	private UUID id;
	
	@Column(name = "title", length = 50, nullable = false)
	private String title;
	
	@Column(name = "type", length = 50, nullable = false)
	private String type;
	
	/* Trong db chưa phải ENUM */
//	@Column(name = "status")
//	@Enumerated(EnumType.STRING)
//	private Status status;
	
	/* Trong db là kiểu ENUM */
	@Column(name = "status", columnDefinition = "enum('OPEN', 'REVIEW', 'APPROVED', 'REJECTED')")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	
	public enum Status {
		OPEN, REVIEW, APPROVED, REJECTED
	}
	
	@Override
	public String toString() {
		return "[id = " + id + "; title = " + title + "; type = " + type + "; status = " + status + "]";
	}

//	public int getId() {
//		return id;
//	}
//
//
//	public void setId(int id) {
//		this.id = id;
	//}
	
	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
	
}
