package wiki.pageobjects.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
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
	public AppiumDriver appiumDriver;

	private int factorialTimeoutBenchmark = 5;

	protected void load() {}

	protected void isLoaded() {}

	public void initialiseElements() {
		PageFactory.initElements(appiumDriver, this);
	}

	public abstract boolean isDisplayed();

	/**
	 Will wait for app pages to load for a maximum factorialTimeoutBenchmark! seconds
	 @throws InterruptedException
	 */
	public void waitTillDisplayed() throws InterruptedException {
		int timeoutParam = getFactorialTimeoutBenchmark();
		do {
			initialiseElements();
			if(isDisplayed()) {break;}
			Thread.sleep(timeoutParam * 1000L);
			timeoutParam--;
		}while(timeoutParam > 0);
	}

	public void setFactorialTimeoutBenchmark(int factorialTimeoutBenchmark) {
		this.factorialTimeoutBenchmark = factorialTimeoutBenchmark;
	}

	public int getFactorialTimeoutBenchmark() {
		return factorialTimeoutBenchmark;
	}

	@FindBy(id = "page_progress_bar")
	private WebElement progressBar;

	public boolean isProgressBarDisplayed() {
		try {
			return progressBar.isDisplayed();
		} catch (Exception e ) {
			return false;
		}
	}

	/**
	 * Wait for articles to load
	 * @throws InterruptedException
	 */
	public void waitForLoadComplete() throws InterruptedException {
		int timeoutParam = getFactorialTimeoutBenchmark();

		int progressBarTimeout = 2;
		while(!isProgressBarDisplayed() && progressBarTimeout > 0) {
			initialiseElements();
			Thread.sleep(1000);
			progressBarTimeout--;
		}

		do {
			initialiseElements();
			if(!isProgressBarDisplayed()) {break;}
			Thread.sleep(timeoutParam * 1000L);
			timeoutParam--;
		}while(timeoutParam > 0);
	}

}