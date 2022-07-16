package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.Account;

public class AccountSpecification {
	
	@SuppressWarnings({ "deprecation", "unused" })
	public static Specification<Account> buildWhere(String nameSearching) {
		Specification<Account> where = null;
		
		nameSearching = nameSearching.trim();
		
		if (!StringUtils.isEmpty(nameSearching)) {
			CustomAccountSpecification searchByUsername = new CustomAccountSpecification("username", nameSearching);
			CustomAccountSpecification searchByfirstName = new CustomAccountSpecification("firstName", nameSearching);
			CustomAccountSpecification searchBylastName = new CustomAccountSpecification("lastName", nameSearching);
			
			where = Specification.where(searchByfirstName).or(searchByfirstName).or(searchBylastName);
		}
		
		return where;
	}
	
}
