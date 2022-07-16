package com.vti;

import java.util.List;

import com.vti.entity.Article;
import com.vti.entity.Article.Status;
import com.vti.respository.ArticleRepository;

public class Program {
	public static void main(String[] args) {
		System.out.println("Buoi2 Java Advance");
		
		ArticleRepository articleRepository = new ArticleRepository();
		
//		Article article = new Article("Java Advance", "Backend", Status.OPEN);
//		Article article2 = new Article("Reactjs", "Frontend", Status.APPROVED);
//		articleRepository.createArticle(article);
//		articleRepository.createArticle(article2);
//		
//		List<Article> articles = articleRepository.getListArticles();
//		
//		for (Article article: articles) {
//			System.out.println(article);
//		}
		
		/*update article's title*/ 
		//articleRepository.updateArticle(1, "Nodejs");
		
		/*delete article*/
		//articleRepository.deletArticle(2);
		
		//System.out.println("List article after delete one item by id: ");
		List<Article> articles = articleRepository.getListArticles();
		
		for (Article art: articles) {
			System.out.println(art);
		}
		
		
	}
}