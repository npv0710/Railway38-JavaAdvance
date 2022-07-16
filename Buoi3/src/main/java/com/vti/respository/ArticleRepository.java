package com.vti.respository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.vti.entity.Article;
import com.vti.utils.HibernateUtils;

public class ArticleRepository {
	private HibernateUtils hibernateUtils;
	
	public ArticleRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}
	
	public void createArticle(Article article) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = hibernateUtils.openSession();
			transaction = session.getTransaction();
			
			transaction.begin();
			
			session.save(article);
			
			transaction.commit();
			
		}catch(Exception ex) {
			System.out.println(ex);
			
			transaction.rollback();
			
		}finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	public List<Article> getListArticles() {
		Session session = null;
		
		try {
			session = hibernateUtils.openSession();
			Query<Article> query = session.createQuery("FROM Article");
			
			return query.list();
		}catch(Exception ex) {
			System.out.println(ex);
		}finally {
			if (session != null) {
				session.close();
			}
		}
		
		return null;
	}
	
	
	public void updateArticle(int id, String title) {
		Session session = null;
		
		Transaction transaction = null;
		
		try {
			session = hibernateUtils.openSession();
			transaction = session.getTransaction();
			
			transaction.begin();
			
			Article article = session.get(Article.class, id);
			
			article.setType(title);
			
			session.save(article);
			
			transaction.commit();
			
		}catch(Exception ex) {
			System.out.println(ex);
			
			transaction.rollback();
		}finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	public void deletArticle(int id) {
		Session session = null;
		
		Transaction transaction = null;
		
		try {
			session = hibernateUtils.openSession();
			transaction = session.getTransaction();
			
			transaction.begin();
			
			Article article = session.get(Article.class, id);
			
			session.delete(article);
			
			transaction.commit();
			
		}catch(Exception ex) {
			System.out.println(ex);
			
			transaction.rollback();
		}finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
