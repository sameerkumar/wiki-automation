package wiki.pageobjects.pages;


import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import io.appium.java_client.MobileElement;

/**
 * Home page object.
 */
@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class HomePage extends BasePage {

	@FindBy(id = "single_fragment_toolbar_wordmark")
	private MobileElement logo;

	@FindBy(id = "menu_overflow_button")
	private MobileElement topMenuButton;

	@FindBy(id = "search_container")
	private MobileElement searchInputBox;

	@FindBy(xpath = "//android.widget.TextView[@text='Explore']")
	private MobileElement exploreNavigationTab;

	@FindBy(xpath = "//android.widget.TextView[@text='My lists']")
	private MobileElement myListsNavigationTab;

	@FindBy(xpath = "//android.widget.TextView[@text='History']")
	private MobileElement historyNavigationTab;

	@FindBy(xpath = "//android.widget.TextView[@text='Nearby']")
	private MobileElement nearByNavigationTab;

	public void clickSearchBox() { searchInputBox.click(); }
	public void clickTopMenuButton(){
		topMenuButton.click();
	}

	public void clickExploreNavigationTab() {
		exploreNavigationTab.click();
	}

	@Override
	public boolean isDisplayed() {
		try{
			return logo.isDisplayed() && topMenuButton.isDisplayed()
					&& searchInputBox.isDisplayed() && exploreNavigationTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
