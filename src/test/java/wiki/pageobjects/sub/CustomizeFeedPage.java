package wiki.pageobjects.sub;

import java.util.NoSuchElementException;

import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import io.appium.java_client.MobileElement;
import wiki.pageobjects.pages.BasePage;

@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class CustomizeFeedPage extends BasePage {

	@FindBy(id="view_announcement_text")
	private MobileElement announcement;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Got it']")
	private MobileElement gotItButton;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Customize']")
	private MobileElement customiseButton;
	
	@Override
	public boolean isDisplayed() {
		try {
			if(customiseButton.getText().equalsIgnoreCase("CUSTOMIZE") && gotItButton.getText().equalsIgnoreCase("GOT IT"))
				return true;
		}catch(Exception e) {
			return false;	
		}
		return false;		
	}
	
	public void clickGotIt() {
		gotItButton.click();
	}

}
