package wiki.tests.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.Application;
import wiki.pageobjects.pages.ArticlePage;
import wiki.pageobjects.pages.HomePage;
import wiki.pageobjects.sub.SearchView;
import wiki.tests.utils.Common;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 *  I test a successful search scenario.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class SearchArticlesTest {

	@Autowired
	Application application;

	@Autowired
	private HomePage homePage;

	@Autowired
	private Common common;

	@Autowired
	private SearchView searchView;

	@Autowired
	private ArticlePage articlePage;

	@Before
	public void before() throws InterruptedException {
		application.prepareForTest();
		common.logoffUser();
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
		assertThat(searchView.isTitleInResults("Wikipedia")).isTrue();
		assertThat(searchView.isTitleDescriptionInResults("Free online encyclopedia that anyone can edit")).isTrue();

		//Select article
		searchView.selectArticle("Wikipedia");
		articlePage.initialiseElements();
		articlePage.waitForLoadComplete();

		// Verify article view
		assertThat(articlePage.getArticleHeader().contains("Wikipedia"));
		assertThat(articlePage.getArticleHeader().contains("Free online encyclopedia that anyone can edit"));
	}

	@After
	public void after() {
		application.cleanUp();
	}
}
