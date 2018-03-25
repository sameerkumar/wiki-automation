package wiki.pageobjects.sub;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import io.appium.java_client.MobileElement;
import wiki.pageobjects.pages.BasePage;

/**
 * Top bar menu page object.
 */
@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class HomePageTopMenu extends BasePage{

	@FindBy(id = "explore_overflow_account_name")
	private MobileElement user;

	@FindBy(id = "explore_overflow_settings")
	private MobileElement settings;

	@FindBy(id = "explore_overflow_donate")
	private MobileElement supportWikipedia;

	@FindBy(id = "explore_overflow_log_out")
	private MobileElement logout;

	public void clickUser() {
		user.click();
	}

	public void clickSettings() {
		settings.click();
	}

	public void clickSupportWikipedia() {
		supportWikipedia.click();
	}

	public void clickLogout() { logout.click(); }

	@Override
	public boolean isDisplayed() {
		try {
			return user.isDisplayed()
					&& settings.isDisplayed() && supportWikipedia.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}

	public boolean isLogoutDisplayed() {
		try {
			return logout.isDisplayed();
		} catch(NoSuchElementException e) {
			return false;
		}
	}

	public String getUsername() {
		return user.getText().trim();
	}
}
