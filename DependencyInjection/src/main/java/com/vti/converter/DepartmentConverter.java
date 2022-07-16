package com.vti.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.vti.entity.Department;
import com.vti.entity.Department.DepartmentType;

@Converter(autoApply = true)
public class DepartmentConverter implements AttributeConverter<Department.DepartmentType, String>{

	@Override
	public String convertToDatabaseColumn(DepartmentType attribute) {
		if (attribute != null) return attribute.getType();
		return null;
	}

	@Override
	public DepartmentType convertToEntityAttribute(String dbData) {
		if (dbData != null) return Department.DepartmentType.toEnum(dbData);
		return null;
	}

}
