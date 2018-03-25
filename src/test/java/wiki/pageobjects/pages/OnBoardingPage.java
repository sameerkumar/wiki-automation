package wiki.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import io.appium.java_client.MobileElement;

@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class OnBoardingPage extends BasePage {

	@FindBy(id="fragment_onboarding_pager_container")
	private MobileElement OnBoardingPage;
	
	@FindBy(id = "org.wikipedia.alpha:id/fragment_onboarding_skip_button")
	private MobileElement skipButton;
	
	@Override
	public boolean isDisplayed() {		
		return OnBoardingPage.isDisplayed();
	}

	public void clickSkipButton() {
		skipButton.click();
	}
	
}
