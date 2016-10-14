package wiki.pageobjects.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

/**
 * Article page object.
 */
@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class ArticlePage extends BasePage{

	@FindBy(id = "view_article_header_container")
	private WebElement headerContainer;

	@FindBy(id = "view_article_header_text")
	private WebElement headerText;

	@Override
	public boolean isDisplayed() {
		try{
			return headerContainer.isDisplayed() && headerText.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getArticleHeader() {
		return headerText.getText();
	}
}

