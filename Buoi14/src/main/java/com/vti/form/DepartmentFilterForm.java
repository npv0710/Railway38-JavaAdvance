package com.vti.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DepartmentFilterForm {
	private String type;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date minDate;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date maxDate;
}
