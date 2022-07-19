package com.vti.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vti.service.IDepartmentService;

public class DepartmentNameNotExistsValidator implements ConstraintValidator<DepartmentNameNotExists, String>{
	@Autowired
	private IDepartmentService dpService;
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if (StringUtils.isEmpty(name)) return true;
		return !dpService.existsByName(name);
	}
}
