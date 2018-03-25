package configuration;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Android Driver Configurations.
 */
@Configuration
@DependsOn("appiumServerConfiguration")
public class AppiumDriverConfiguration {

	@Autowired
	private AppiumDriverLocalService appiumDriverLocalService;

	@Value("${appium.port}")
	private int port;

	@Value("${app.dir}")
	private String appDir;

	@Value("${app}")
	private String appName;

	@Value("${appium.host}")
	private String host;
	
	@Autowired
	private String appMainActivity;

	private String getAppiumServiceURL() {
		return appiumDriverLocalService.getUrl().toString();
	}

	@Bean
	public AppiumDriver androidDriver() throws MalformedURLException {
		appiumDriverLocalService.start();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", appDir.concat(appName));
		capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "org.wikipedia.*");		
		capabilities.setCapability("noReset", "true");
		capabilities.setCapability("fullReset", "false");
		capabilities.setCapability("deviceName", "Test-Device");
		capabilities.setCapability("enablePerformanceLogging", "true");
		return new AndroidDriver(new URL(getAppiumServiceURL()), capabilities);
	}

	@Bean String appPath() {
		return appDir.concat(appName);
	}
}

