package wiki.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.Application;
import wiki.pageobjects.pages.HomePage;
import wiki.pageobjects.sub.HomePageTopMenu;
import wiki.tests.utils.Common;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 *  Sanity Test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class SanityTest {

	@Autowired
	Application application;

	@Autowired
	private HomePage homePage;

	@Autowired
	private Common common;

	@Autowired
	private HomePageTopMenu homePageTopMenu;

	@Before
	public void before() throws InterruptedException {
		application.prepareForTest();
		common.logoffUser();
	}

	@Test
	public void checkApp() throws InterruptedException {
		homePage.waitTillDisplayed();
		assertThat(homePage.isDisplayed()).isTrue();

		homePage.clickTopMenuButton();
	 	homePageTopMenu.initialiseElements();
		assertThat(homePageTopMenu.isDisplayed()).isTrue();
	}

	@After
	public void after() {
		application.cleanUp();
	}
}
