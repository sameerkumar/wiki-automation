package wiki.tests.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.Application;
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

	@Before
	public void before() throws InterruptedException {
		application.prepareForTest();
		common.logoffUser();
	}

	@Test
	public void whenUserEntersValidCredentialsThenLoginIsSuccessful() throws InterruptedException {
		// Navigate to login page
		homePage.initialiseElements();
		homePage.clickSearchBox();

		searchView.initialiseElements();
		searchView.setSearchText("Wikipedia");

		searchView.initialiseElements();
		assertThat(searchView.isTitleInResults("Wikipedia")).isTrue();
		assertThat(searchView.isTitleDescriptionInResults("Free online encyclopedia that anyone can edit")).isTrue();
	}

	@After
	public void after() {
		application.cleanUp();
	}
}
