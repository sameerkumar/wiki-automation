package utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Get translations.
 */
public class Message {

	public static String getMessage(String key) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("messages.messages");
		return resourceBundle.getString(key);
	}

	public static String getMessage(String key, Locale locale) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("messages.messages", locale);
		return resourceBundle.getString(key);
	}

}
