package wiki.pageobjects.pages;

import io.appium.java_client.AppiumDriver;
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
	 Will wait for maximum factorialTimeoutBenchmark!	seconds
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

}