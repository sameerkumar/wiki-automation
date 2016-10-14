package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Application {

	@Autowired
	private AppiumDriver appiumDriver;

	@Autowired
	private String appPath;

	@Autowired
	private String appPackage;

	@Autowired
	private String appMainActivity;

	public void launchApp() {
		appiumDriver.launchApp();
	}

	public void closeApp() {
		appiumDriver.closeApp();
	}

	public void navigateBack() {
		appiumDriver.navigate().back();
	}

	public void navigateForward() {
		appiumDriver.navigate().forward();
	}

	public void refresh() {
		appiumDriver.navigate().refresh();
	}

	public void reset() {
		((AndroidDriver)appiumDriver).startActivity(appPackage, appMainActivity,
				appPackage, appMainActivity,false);
	}

	public void prepareForTest() throws InterruptedException {
		if(!appiumDriver.isAppInstalled(appPackage)) {
			appiumDriver.installApp(appPath);
		}
		reset();
	}

	public void cleanUp() {
	}
}
