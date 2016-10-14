package wiki.pageobjects.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class HomePage extends BasePage {

	@FindBy(id = "single_fragment_toolbar_wordmark")
	private WebElement logo;

	@FindBy(id = "menu_overflow_button")
	private WebElement topMenuButton;

	@FindBy(id = "search_container")
	private WebElement searchInputBox;

	@FindBy(xpath = "//android.widget.TextView[@text='Explore']")
	private WebElement exploreNavigationTab;

	@FindBy(xpath = "//android.widget.TextView[@text='My lists']")
	private WebElement myListsNavigationTab;

	@FindBy(xpath = "//android.widget.TextView[@text='History']")
	private WebElement historyNavigationTab;

	@FindBy(xpath = "//android.widget.TextView[@text='Nearby']")
	private WebElement nearByNavigationTab;

	public void setSearchText(String searchString) {
		searchInputBox.sendKeys(searchString);
	}

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
