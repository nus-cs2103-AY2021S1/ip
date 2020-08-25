package duke.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Interface through which string resources are retrieved.
 */
public class ResourceHandler {
    /** Default locale - set to English/Singapore */
    private static final Locale defaultLocale = new Locale("en", "SG");
    /** For caching the `ResourceBundle` objects of each locale */
    private static final Map<Locale, ResourceBundle> resourceBundles = new HashMap<>();

    /**
     * Returns the string resource for the given key and locale.
     *
     * @param key the identifier for the string resource.
     * @param locale the locale of the string.
     * @return the string resource.
     */
    public static String getString(String key, Locale locale) {
        resourceBundles.putIfAbsent(locale, ResourceBundle.getBundle("Strings", locale));
        return resourceBundles.get(locale).getString(key);
    }

    /**
     * Returns the string resource for the given key in the default locale (English/Singapore).
     *
     * @param key the identifier for the string resource.
     * @return the string resource.
     */
    public static String getString(String key) {
        return getString(key, defaultLocale);
    }
}
