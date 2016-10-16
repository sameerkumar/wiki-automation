package wiki.pageobjects.sub;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import wiki.pageobjects.pages.BasePage;

import java.util.List;

/**
 * Language search view page object.
 */
@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class LanguageSearchView extends BasePage{

	@FindBy(id = "preference_languages_filter")
	private WebElement searchInputBox;

	@FindBy(id = "language_subtitle")
	private List<WebElement> preferenceLanguages;

	@Override
	public boolean isDisplayed() {
		try {
			return searchInputBox.isDisplayed() && preferenceLanguages.size() != 0;
		} catch(Exception e) {
			return false;
		}
	}

	public void setLanguageSearch(String language) {
		searchInputBox.sendKeys(language);
	}

	public void selectLanguage(String language) {
		for(WebElement lang: preferenceLanguages) {
			if(lang.getText().trim().equalsIgnoreCase(language)){
				lang.click();
				break;
			}
		}
	}
}
