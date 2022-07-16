package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.Account;
import com.vti.utils.HibernateUtils;

public class AccountRepository implements IAccountRepository {
	
	private HibernateUtils hibernateUtils;
	
	public AccountRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getListAccounts() {
		Session session = null;
		
		try {
			session = hibernateUtils.openSession();
			Query<Account> query = session.createQuery("FROM Account");
			
			return query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}


	@Override
	public Account getAccountById(int id) {
		
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			
			Account ac = session.get(Account.class, id);
			
			return ac;
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Account getAccountByUsername(String username) {
		
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			
			Query<Account> query = session.createQuery("FROM Account WHERE username =: parameterUsername");
			
			query.setParameter("parameterUsername", username);
			
			Account ac = (Account) query.uniqueResult();
			
			return ac;
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
