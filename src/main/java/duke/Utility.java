package duke;

import java.io.InputStream;

/**
 * Utility class for Duke
 */
public class Utility {

    /**
     * Get application resource
     * @param path Path from resource folder
     * @return InputStream of the resource
     */
    public static InputStream getResource(String path) {
        // @@author akgrenSoar-reused
        // Source: https://stackoverflow.com/a/15749281/6943913
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(path);
    }

}
