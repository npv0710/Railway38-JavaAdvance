package com.vti.validation;

import com.vti.validation.DepartmentNameNotExists.List;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { DepartmentNameNotExistsValidator.class })
@Repeatable(List.class)
public @interface DepartmentNameNotExists {
	String message() default "Department name already exists";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default{};
	
	@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		DepartmentNameNotExists[] value();
	}
}
