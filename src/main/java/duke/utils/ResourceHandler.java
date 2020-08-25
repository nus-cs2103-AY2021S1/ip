package duke.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Map;

public class ResourceHandler {
    private static final Locale defaultLocale = new Locale("en", "SG");
    private static final String filename = "Messages";
    private static final Map<Locale, ResourceBundle> dukeResources = new HashMap<>();


    public static String getResource(String key, Locale locale) {
        ResourceBundle localeMessages = ResourceBundle.getBundle(filename, locale);
        dukeResources.putIfAbsent(locale, localeMessages);
        localeMessages = dukeResources.get(locale);
        String message = localeMessages.getString(key);
        return message;
    }

    public static String getMessage(String key) {
        return getResource(key, defaultLocale);
    }

}
