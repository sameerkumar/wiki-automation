package utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppiumServer {

	@Autowired
	private AppiumDriverLocalService appiumDriverLocalService;

	public void start(){
		if(!isRunning()) { appiumDriverLocalService.start(); }
	}

	public void stop(){
		if(isRunning()) { appiumDriverLocalService.stop(); }
	}

	public void restart() {
		stop();
		start();
	}

	public boolean isRunning() {
		return appiumDriverLocalService.isRunning();
	}
}
