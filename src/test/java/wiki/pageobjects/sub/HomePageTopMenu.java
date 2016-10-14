package wiki.pageobjects.sub;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import wiki.pageobjects.pages.BasePage;

/**
 * Created by kumarsam on 12/10/2016.
 */
@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class HomePageTopMenu extends BasePage{

	@FindBy(id = "explore_overflow_account_name")
	private WebElement user;

	@FindBy(id = "explore_overflow_settings")
	private WebElement settings;

	@FindBy(id = "explore_overflow_donate")
	private WebElement supportWikipedia;

	@FindBy(id = "explore_overflow_log_out")
	private WebElement logout;

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
