package kr.tagnote.article;

import static org.junit.Assert.*;

import java.util.List;

import kr.tagnote.Application;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ArticleServiceTests {
/*	@Autowired
	ArticleService articleService;

	@Test
//	@Ignore
	public void findAll() {
		Pageable pageable = new PageRequest(0, 10);

		Page<Article.Response> pages = articleService.findByPage(pageable);
		List<Article.Response> articles = pages.getContent();

		for (int i = 0; i < articles.size(); i++) {
			System.out.println(articles.get(i).getTags().get(0).getTag().getName());
		}
	}

	@Test
//	@Ignore
	public void findOne() {
		Article.Response article = articleService.findById(26);
		System.out.println(article.getTags().get(0).getTag().getName());
	}

	@Test
//	@Ignore
	public void findAll1() {
		Pageable pageable = new PageRequest(0, 10);

		Page<Article.Response> pages = articleService.findByPage(pageable);
		List<Article.Response> articles = pages.getContent();

		for (int i = 0; i < articles.size(); i++) {
			System.out.println(articles.get(i).getTags().get(0).getTag().getName());
		}
	}

	@Test
//	@Ignore
	public void findOne1() {
		Article.Response article = articleService.findById(26);
		System.out.println(article.getTags().get(0).getTag().getName());
	}*/
}