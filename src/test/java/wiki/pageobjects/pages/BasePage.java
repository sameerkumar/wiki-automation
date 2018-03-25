package wiki.pageobjects.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public abstract class BasePage extends LoadableComponent<BasePage> {

	@Autowired
	private AppiumDriver appiumDriver;

	private int generalTimeoutFactor = 5;

	protected void load() {}

	protected void isLoaded() {}

	public final void initialiseElements() {
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
	}

	public abstract boolean isDisplayed();

	public final AppiumDriver getAppiumDriver() { return appiumDriver; }

	/**
	 * Will wait for pages to load.
	 */
	public final void waitTillDisplayed() throws InterruptedException {
		int timeoutParam = getGeneralTimeoutFactor();
		do {
			initialiseElements();
			try {
				if(isDisplayed()) {break;}
			}catch(Exception e) {}
			Thread.sleep(timeoutParam * 1000L);
			timeoutParam--;
		}while(timeoutParam > 0);
		initialiseElements();
	}

	public final void setGeneralTimeoutFactor(int generalTimeoutFactor) {
		this.generalTimeoutFactor = generalTimeoutFactor;
	}

	public final int getGeneralTimeoutFactor() {
		return generalTimeoutFactor;
	}

	@FindBy(id = "page_progress_bar")
	private MobileElement progressBar;

	public final boolean isProgressBarDisplayed() {
		try {
			return progressBar.isDisplayed();
		} catch (Exception e ) {
			return false;
		}
	}

	/**
	 * Wait method for pages with progress bar.
	 */
	public final void waitForLoadComplete() throws InterruptedException {
		int timeoutParam = getGeneralTimeoutFactor();
		int progressBarTimeout = 2;

		// Give some time for progress bar to appear.
		while(!isProgressBarDisplayed() && progressBarTimeout > 0) {
			initialiseElements();
			Thread.sleep(1000);
			progressBarTimeout--; }
		// Wait for progress bar to disappear.
		do {
			initialiseElements();
			if(!isProgressBarDisplayed()) {break;}
			Thread.sleep(timeoutParam * 1000L);
			timeoutParam--;	}
		while(timeoutParam > 0);
	}

}