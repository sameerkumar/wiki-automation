package wiki.tests.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.Application;
import utils.Message;
import wiki.pageobjects.pages.ArticlePage;
import wiki.pageobjects.pages.HomePage;
import wiki.pageobjects.sub.ArticleSearchView;
import wiki.tests.utils.Common;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 *  I test a successful search scenario.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class SearchArticlesTest {

	@Autowired
	private Application application;

	@Autowired
	private HomePage homePage;

	@Autowired
	private Common common;

	@Autowired
	private ArticleSearchView searchView;

	@Autowired
	private ArticlePage articlePage;

	@Before
	public void before() throws InterruptedException {
		application.prepareForTest();
		common.waitForAppToLaunch();
	}

	@Test
	public void aUserCanSearchAndViewAnArticle() throws InterruptedException {
		// Enter search text
		homePage.initialiseElements();
		homePage.clickSearchBox();

		searchView.initialiseElements();
		searchView.setSearchText("Wikipedia");

		// Verify search matches
		searchView.initialiseElements();
		assertThat(searchView.isTitleInResults(Message.getMessage("wikipedia.article.header"))).isTrue();
		assertThat(searchView.isTitleDescriptionInResults(Message.getMessage("wikipedia.article.description"))).isTrue();

		//Select article
		searchView.selectArticle(Message.getMessage("wikipedia.article.header"));

		// Verify article view
		articlePage.waitForLoadComplete();
		articlePage.initialiseElements();
		assertThat(articlePage.getArticleHeader().contains(Message.getMessage("wikipedia.article.header")));
		assertThat(articlePage.getArticleHeader().contains(Message.getMessage("wikipedia.article.description")));
	}

	@After
	public void after() {
		application.cleanUp();
	}
}
