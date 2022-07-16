package com.vti.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.vti.entity.RegularEmployee;
import com.vti.utils.HibernateUtils;

public class EmployeeRepository implements IEmployeeRepository{
	private HibernateUtils hibernateUtils;
	
	public EmployeeRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegularEmployee> getListRegularEmployeees() {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			
			Query<RegularEmployee> query = session.createQuery("FROM RegularEmployee");
			
			return query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<RegularEmployee> getListRegularEmployeeesSearchByName(String name) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			
			Criteria criteria = session.createCriteria(RegularEmployee.class);
			
			Criterion likeName = Restrictions.ilike("name", "%" + name + "%");
			
			int minSalary = 2000;
			
			Criterion gtSalary = Restrictions.gt("salary", minSalary);
			
			LogicalExpression andExp = Restrictions.and(likeName, gtSalary);
			
			LogicalExpression orExp = Restrictions.or(gtSalary, likeName);
			
//			criteria.add(likeName);
//			criteria.add(gtSalary);
			
			//criteria.add(andExp);
			
			criteria.add(orExp);
			
			
			return criteria.list();
			
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
}
