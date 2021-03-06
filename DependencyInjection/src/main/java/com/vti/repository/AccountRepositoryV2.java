package com.vti.repository;

import org.hibernate.Session;

import com.vti.entity.Account;

public class AccountRepositoryV2 extends AccountRepository{
	
	public void createAccount(Account ac) {
		Session session = null;
		
		try {
			session = hibernateUtil.openSession();
			
			session.save(ac);
			
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
