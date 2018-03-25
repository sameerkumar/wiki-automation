package wiki.pageobjects.sub;

import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import io.appium.java_client.MobileElement;
import wiki.pageobjects.pages.BasePage;

import java.util.List;

/**
 * Settings view page object.
 */
@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class SettingsView extends BasePage{

	@FindBy(id="action_bar_container")
	private MobileElement headerBar;

	@FindBy(id="title")
	private List<MobileElement> settingOptions;
	
	@FindBy(id="summary")
	private List<MobileElement> summaries;

	@Override
	public boolean isDisplayed() {
		try {
			return getHeaderText().equals("Settings") && settingOptions.size() != 0;
		} catch(Exception e) {
			return false;
		}
	}

	public String getHeaderText() {
		return headerBar.getText().trim();
	}

	public void selectOption(String settingOption) {
		for(MobileElement option: settingOptions) {
			if(option.getText().trim().equals(settingOption)) {
				option.click();
				break;
			}
		}
	}
	
	public String GetSummaryForOption(String settingOption) {
		int index=0;
		for(MobileElement option: settingOptions) {			
			if(option.getText().trim().equals(settingOption)) {
				return summaries.get(index).getText().trim();
			}
			index++;
		}
		return "";
	}
}
