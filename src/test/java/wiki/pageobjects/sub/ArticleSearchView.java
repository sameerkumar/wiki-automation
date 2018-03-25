package wiki.pageobjects.sub;

import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import io.appium.java_client.MobileElement;
import wiki.pageobjects.pages.BasePage;

import java.util.List;

/**
 * Search view page object.
 */
@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class ArticleSearchView extends BasePage{

	@FindBy(id = "search_src_text")
	private MobileElement searchInputBox;

	@FindBy( id = "search_close_btn")
	private MobileElement searchCloseButton;

	@FindBy( id = "search_lang_button")
	private MobileElement searchLanguageButton;

	@FindBy(id = "page_list_item_title")
	private List<MobileElement> matchingTitles;

	@FindBy(id = "page_list_item_description")
	private List<MobileElement> matchingTitleDescriptions;

	@Override
	public boolean isDisplayed() {
		try {
			return searchInputBox.isDisplayed() &&
					searchCloseButton.isDisplayed() && searchLanguageButton.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}

	public void setSearchText(String searchText) {
		searchInputBox.sendKeys(searchText);
	}

	public boolean isTitleInResults(String titleText) {
		for(MobileElement title: matchingTitles) {
				if(title.getText().trim().equals(titleText)) {
					return true;
				}
		}
		return false;
	}

	public boolean isTitleDescriptionInResults(String description) {
		for(MobileElement titleDescription: matchingTitleDescriptions) {
			if(titleDescription.getText().trim().equals(description)) {
				return true;
			}
		}
		return false;
	}

	public void selectArticle(String titleText) {
		for(MobileElement title: matchingTitles) {
			if(title.getText().trim().equals(titleText)) {
				title.click();
				break;
			}
		}
	}
}
