package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.Department;
import com.vti.utils.HibernateUtils;

public class DepartmentRepository implements IDepartmentRepository{
	
	private HibernateUtils hibernateUtil;
	
	public DepartmentRepository() {
		hibernateUtil = HibernateUtils.getInstance();
	}
	
	@Override
	public List<Department> getListDepartments() {
		Session session = null;
		
		try {
			session = hibernateUtil.openSession();
			
			Query<Department> query = session.createQuery("FROM Department");
			
			return query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@Override
	public int createDepartment(Department dp) {
		Session session = null;
		
		try {
			session = hibernateUtil.openSession();
			session.getTransaction().begin();
			
			int departmentId = (Integer)session.save(dp);
			
			session.getTransaction().commit();
			
			return departmentId;
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
