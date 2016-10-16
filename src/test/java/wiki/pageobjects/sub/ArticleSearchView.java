package wiki.pageobjects.sub;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import wiki.pageobjects.pages.BasePage;

import java.util.List;

/**
 * Search view page object.
 */
@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class ArticleSearchView extends BasePage{

	@FindBy(id = "search_src_text")
	private WebElement searchInputBox;

	@FindBy( id = "search_close_btn")
	private WebElement searchCloseButton;

	@FindBy( id = "search_lang_button")
	private WebElement searchLanguageButton;

	@FindBy(id = "page_list_item_title")
	private List<WebElement> matchingTitles;

	@FindBy(id = "page_list_item_description")
	private List<WebElement> matchingTitleDescriptions;

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
		for(WebElement title: matchingTitles) {
				if(title.getText().trim().equals(titleText)) {
					return true;
				}
		}
		return false;
	}

	public boolean isTitleDescriptionInResults(String description) {
		for(WebElement titleDescription: matchingTitleDescriptions) {
			if(titleDescription.getText().trim().equals(description)) {
				return true;
			}
		}
		return false;
	}

	public void selectArticle(String titleText) {
		for(WebElement title: matchingTitles) {
			if(title.getText().trim().equals(titleText)) {
				title.click();
				break;
			}
		}
	}
}
