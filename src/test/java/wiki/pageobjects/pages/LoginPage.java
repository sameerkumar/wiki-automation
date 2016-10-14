package wiki.pageobjects.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class LoginPage extends BasePage {

	@FindBy(id = "login_username_text")
	private WebElement usernameInputBox;

	@FindBy(id = "password_edit_text_input")
	private WebElement passwordInputBox;

	@FindBy(id = "login_button")
	private WebElement loginButton;

	@FindBy(id = "login_create_account_link")
	private WebElement createAccountLink;

	public void setUsername(String username) {
		usernameInputBox.sendKeys(username);
	}

	public void setPasswordInputBox(String password) {
		passwordInputBox.sendKeys(password);
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
