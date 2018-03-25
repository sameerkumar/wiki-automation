package wiki.pageobjects.sub;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.appium.java_client.MobileElement;
import wiki.pageobjects.pages.BasePage;

@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class AlertPage extends BasePage{
	
	@FindBy(id = "parentPanel")
	private MobileElement alertPanel;

	@FindBy(id = "alertTitle")
	private MobileElement alertTitle;
	
	@FindBy(id = "button2")
	private MobileElement noButton;
	
	@FindBy(id = "dialog_checkbox")
	private MobileElement checkBox;
	
	@Override
	public boolean isDisplayed() {
		if(alertPanel.isDisplayed() && alertTitle.isDisplayed())
			return true;
		return false;
	}
	
	public void clickNo() {
		alertPanel.findElement(By.id("button2")).click();
	}
	
	public void clickCheckBox() {
		checkBox.click();
	}
}
