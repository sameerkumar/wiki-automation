package configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Test Configurations.
 */
@Configuration
@PropertySource("classpath:test.properties")
public class TestConfigurations {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

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

