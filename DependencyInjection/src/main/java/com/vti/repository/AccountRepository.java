package com.vti.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.Account;
import com.vti.utils.HibernateUtils;

public class AccountRepository implements IAccountRepository{
	protected HibernateUtils hibernateUtil;
	
	public AccountRepository() {
		hibernateUtil = HibernateUtils.getInstance();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Account> getListAccount() {
		Session session = null;
		
		try {
			session = hibernateUtil.openSession();
			
			Query query = session.createQuery("FROM Account");
			
			return query.list();
			
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@Override
	public Optional<Account> findAccountById(int id) {
		return null;
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

}
