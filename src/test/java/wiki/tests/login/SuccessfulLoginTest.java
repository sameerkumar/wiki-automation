package wiki.tests.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.ApplicationUtil;
import wiki.pageobjects.pages.HomePage;
import wiki.pageobjects.pages.LoginPage;
import wiki.pageobjects.sub.AlertPage;
import wiki.pageobjects.sub.HomePageTopMenu;
import wiki.tests.utils.Common;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 *  I test a successful login scenario.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
@DirtiesContext
public class SuccessfulLoginTest {

	@Autowired
	private ApplicationUtil application;

	@Autowired
	private HomePage homePage;

	@Autowired
	private HomePageTopMenu homePageTopMenu;

	@Autowired
	private LoginPage loginPage;
	
	@Autowired
	private AlertPage alertPage;

	@Autowired
	private Common common;

	@Before
	public void before() throws InterruptedException {
		application.prepareForTest();
		common.waitForAppToLaunch();
		common.logoffUser();
	}

	@Test
	public void whenUserEntersValidCredentialsThenLoginIsSuccessful() throws InterruptedException {
		// Navigate to login page
		homePage.initialiseElements();
		homePage.clickTopMenuButton();
		homePageTopMenu.initialiseElements();
		homePageTopMenu.clickUser();

		// Enter credentials
		loginPage.waitTillDisplayed();		
		loginPage.setPasswordInputBox("madtester123"); // Test credentials, no personal data
		loginPage.setUsername("madtester"); // Test credentials, no personal data
		loginPage.clickLoginButton();

		alertPage.waitTillDisplayed();		
		if(alertPage.isDisplayed()) {
			alertPage.clickCheckBox();
			alertPage.clickNo();
		}
			
		
		// Verify
		homePage.waitTillDisplayed();
		assertThat(homePage.isDisplayed()).isTrue();

		homePage.initialiseElements();
		homePage.clickTopMenuButton();
		homePageTopMenu.initialiseElements();
		assertThat(homePageTopMenu.isLogoutDisplayed()).isTrue();
		assertThat(homePageTopMenu.getUsername()).isEqualToIgnoringCase("madtester");

		application.navigateBack();
	}

	@After
	public void after() {
		application.cleanUp();
	}
}
