package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import wiki.pageobjects.pages.OnBoardingPage;
import wiki.pageobjects.sub.CustomizeFeedPage;
import wiki.tests.utils.Common;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUtil {

	@Autowired
	private AppiumDriver appiumDriver;

	@Autowired
	private CustomizeFeedPage customizeFeedPage;
	
	@Autowired
	private OnBoardingPage onBoardingPage;
	
	@Autowired
	private String appPath;

	@Autowired
	private String appPackage;

	@Autowired
	private String appMainActivity;
	
	@Autowired
	private Common common;

	public void launchApp() {
		appiumDriver.launchApp();
	}

	public void closeApp() {
		appiumDriver.closeApp();
	}

	public void navigateBack() {
		appiumDriver.navigate().back();
	}

	public void navigateForward() {
		appiumDriver.navigate().forward();
	}

	public void refresh() {
		appiumDriver.navigate().refresh();
	}

	public void reset() {		
		Activity activity = new Activity(appPackage, appMainActivity);
		((AndroidDriver)appiumDriver).startActivity(new Activity(appPackage, appMainActivity));		
	}

	public void prepareForTest() throws InterruptedException {
		if(!appiumDriver.isAppInstalled(appPackage)) {
			appiumDriver.installApp(appPath);
		}
		
		if(((AndroidDriver)appiumDriver).currentActivity().equalsIgnoreCase("org.wikipedia.onboarding.InitialOnboardingActivity")) {
			onBoardingPage.initialiseElements();
			onBoardingPage.clickSkipButton();
		}else {
			reset();
		}
		common.updateLanguage("English");
	}

	public void cleanUp() {		
	}

	public void hideKeyboard() {
		appiumDriver.hideKeyboard();
	}
}
