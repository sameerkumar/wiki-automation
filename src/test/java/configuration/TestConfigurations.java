package configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Test Configurations.
 */
@Configuration
public class TestConfigurations {

	@Value("${app.package}")
	private String appPackage;

	@Value("${app.main.activity}")
	private String appMainActivity;

	@Bean
	public String appPackage() {
		return appPackage;
	}

	@Bean String appMainActivity() {
		return appMainActivity;
	}
}

