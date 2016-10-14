package configuration;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Android Driver Configurations.
 */
@Configuration
@PropertySource("classpath:test.properties")
@DependsOn("appiumServerConfiguration")
public class AppiumDriverConfiguration {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

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

	private String getAppiumServiceURL() {
		return appiumDriverLocalService.getUrl().toString();
		//return "http://" + host + ":" + port + "/wd/hub";
	}

	@Bean
	public AppiumDriver androidDriver() throws MalformedURLException {
		appiumDriverLocalService.start();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", appDir.concat(appName));
		capabilities.setCapability("noReset", "true");
		capabilities.setCapability("fullReset", "false");
		capabilities.setCapability("deviceName", "Nexus-Emulator");
		return new AndroidDriver(new URL(getAppiumServiceURL()), capabilities);
	}

	@Bean String appPath() {
		return appDir.concat(appName);
	}
}

