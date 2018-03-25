package wiki.tests.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.ApplicationUtil;
import wiki.pageobjects.pages.HomePage;
import wiki.pageobjects.pages.LoginPage;
import wiki.pageobjects.sub.HomePageTopMenu;
import wiki.pageobjects.sub.LanguageSearchView;
import wiki.pageobjects.sub.SettingsView;

@Component
public class Common {

	@Autowired
	private ApplicationUtil application;

	@Autowired
	private HomePage homePage;

	@Autowired
	private HomePageTopMenu homePageTopMenu;

	@Autowired
	private LoginPage loginPage;

	@Autowired
	private SettingsView settingsView;

	@Autowired
	private LanguageSearchView languageSearchView;

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

	public void updateLanguage(String language) throws InterruptedException {
		homePage.initialiseElements();
		homePage.clickTopMenuButton();

		homePageTopMenu.initialiseElements();
		homePageTopMenu.clickSettings();

		settingsView.initialiseElements();
		settingsView.waitForLoadComplete();

		if(!settingsView.GetSummaryForOption("Wikipedia language").equals(language)) {
			
			settingsView.selectOption("Wikipedia language");	
			languageSearchView.initialiseElements();
			languageSearchView.setLanguageSearch(language);
			languageSearchView.initialiseElements();
			languageSearchView.selectLanguage(language);
		}

		application.navigateBack();
	}

	public void waitForAppToLaunch() throws InterruptedException {
		homePage.waitTillDisplayed();
	}

}
