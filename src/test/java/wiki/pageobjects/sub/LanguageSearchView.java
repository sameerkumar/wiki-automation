package wiki.pageobjects.sub;

import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import io.appium.java_client.MobileElement;
import wiki.pageobjects.pages.BasePage;

import java.util.List;

/**
 * Language search view page object.
 */
@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class LanguageSearchView extends BasePage{

	@FindBy(id = "preference_languages_filter")
	private MobileElement searchInputBox;

	@FindBy(id = "localized_language_name")
	private List<MobileElement> localisedLanguageNames;
	
	@FindBy(id = "language_subtitle")
	private List<MobileElement> languageSubtitles;

	@Override
	public boolean isDisplayed() {
		try {
			return searchInputBox.isDisplayed() && localisedLanguageNames.size() != 0;
		} catch(Exception e) {
			return false;
		}
	}

	public void setLanguageSearch(String language) {
		searchInputBox.sendKeys(language);
	}

	public void selectLanguage(String language) {
		if(localisedLanguageNames.size()==1) {
			localisedLanguageNames.get(0).click();
		}else {
			int index=0;
			for(MobileElement lang: localisedLanguageNames) {
				if(lang.getText().trim().equalsIgnoreCase(language) || languageSubtitles.get(index).getText().trim().equalsIgnoreCase(language)){
					lang.click();
					break;
				}
				index++;
			}
		}		
	}
}
