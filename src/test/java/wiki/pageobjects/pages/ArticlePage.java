package wiki.pageobjects.pages;

import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import io.appium.java_client.MobileElement;

/**
 * Article page object.
 */
@Component
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
public class ArticlePage extends BasePage{

	@FindBy(id = "view_article_header_container")
	private MobileElement headerContainer;

	@FindBy(id = "org.wikipedia.alpha:id/view_page_title_text")
	private MobileElement headerText;
	
	@FindBy(id = "org.wikipedia.alpha:id/view_page_subtitle_text")
	private MobileElement subHeaderText;
	

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
	
	public String getArticleSubHeader() {
		return subHeaderText.getText();
	}
}

