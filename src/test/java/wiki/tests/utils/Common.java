package wiki.tests.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.Application;
import wiki.pageobjects.pages.HomePage;
import wiki.pageobjects.pages.LoginPage;
import wiki.pageobjects.sub.HomePageTopMenu;

@Component
public class Common {

	@Autowired
	Application application;

	@Autowired
	private HomePage homePage;

	@Autowired
	private HomePageTopMenu homePageTopMenu;

	@Autowired
	private LoginPage loginPage;

	public void logoffUser() throws InterruptedException {
		homePageTopMenu.initialiseElements();
		if(homePageTopMenu.isDisplayed()) {
			if( homePageTopMenu.isLogoutDisplayed()) {
				homePageTopMenu.clickLogout();
			} else {
				application.navigateBack();
			}
		}else {
			homePage.initialiseElements();
			homePage.clickTopMenuButton();
			homePageTopMenu.initialiseElements();
			if(homePageTopMenu.isLogoutDisplayed()) {
				homePageTopMenu.clickLogout();
			}else {
				application.navigateBack();
			}
		}
	}
}
