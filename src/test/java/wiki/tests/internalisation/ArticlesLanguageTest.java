package wiki.tests.internalisation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import utils.ApplicationUtil;
import utils.Internationalisation;
import utils.Message;
import wiki.pageobjects.pages.ArticlePage;
import wiki.pageobjects.pages.HomePage;
import wiki.pageobjects.sub.ArticleSearchView;
import wiki.tests.utils.Common;

import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 *  Articles are viewed in language set in the app.
 */
@RunWith(Parameterized.class)
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
@DirtiesContext
public class ArticlesLanguageTest {

	@Autowired
	private ApplicationUtil application;

	@Autowired
	private HomePage homePage;

	@Autowired
	private Common common;

	@Autowired
	private ArticleSearchView searchView;

	@Autowired
	private ArticlePage articlePage;

	private TestContextManager testContextManager;

	private Locale locale;

	public ArticlesLanguageTest(Locale locale) {
		this.locale = locale;
	}

	@Before
	public void createSpringTestContext() throws Exception {
		this.testContextManager = new TestContextManager(getClass());
		this.testContextManager.prepareTestInstance(this);
		application.prepareForTest();
		common.waitForAppToLaunch();
		common.logoffUser();
	}

	@Parameterized.Parameters
	public static List<Locale[]> locales() {
		return asList(Internationalisation.supportedLocales());
	}

	@Test
	public void articlesAreDisplayedInTheLanguageSpecifiedInTheApp() throws InterruptedException {
		// Update language
		common.updateLanguage(locale.getDisplayLanguage());
		
		// Enter search text
		homePage.initialiseElements();
		homePage.clickSearchBox();

		searchView.initialiseElements();
		searchView.waitForLoadComplete();
		searchView.setSearchText("Wikipedia");

		//Select article
		searchView.initialiseElements();
		searchView.selectArticle(Message.getMessage("wikipedia.article.header", locale));

		// Verify article view
		articlePage.waitForLoadComplete();
		articlePage.initialiseElements();
		assertThat(articlePage.getArticleHeader()
				.contains(Message.getMessage("wikipedia.article.header", locale)));
		assertThat(articlePage.getArticleHeader()
				.contains(Message.getMessage("wikipedia.article.description", locale)));

		application.navigateBack();
	}

	@After
	public void after() throws InterruptedException {
		// revert to default		
		application.cleanUp();
	}
}
