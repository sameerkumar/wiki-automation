package wiki.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import io.appium.java_client.MobileElement;

@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class LoginPage extends BasePage {

	@FindBy(id = "login_username_text")
	private MobileElement usernameInputBox;

	@FindBy(id = "login_password_input")
	private MobileElement passwordInputBox;

	@FindBy(id = "login_button")
	private MobileElement loginButton;

	@FindBy(id = "login_create_account_link")
	private MobileElement createAccountLink;

	public void setUsername(String username) {
		usernameInputBox.findElement(By.className("android.widget.EditText")).sendKeys(username);
	}

	public void setPasswordInputBox(String password) {
		passwordInputBox.findElement(By.className("android.widget.EditText")).sendKeys(password);
	}

	public void clickLoginButton() {
		loginButton.click();
	}

	public void clickCreateAccountLink() {
		createAccountLink.click();
	}

	@Override
	public boolean isDisplayed() {
		try {
			return usernameInputBox.isDisplayed()
					&& passwordInputBox.isDisplayed() && loginButton.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}
}
