package duke.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

/**
 * Responsible for saving and loading the configuration of the application from a file.
 */
public class ConfigManager {
    /** {@code StorageManager} object for writing the application config to the specified file. */
    private final StorageManager storageManager;
    /** {@code Properties} object for manipulating the application properties. */
    private final Properties properties;

    /**
     * Constructs a new {@code ConfigManager} object with the specified config file.
     *
     * @param filePath the path of the config file.
     */
    public ConfigManager(String filePath) {
        storageManager = new StorageManager(filePath);
        properties = readStateFromFile();
    }

    /**
     * Writes the current state of the application properties to the specified file.
     */
    private void syncStateToFile() {
        try {
            StringWriter stringWriter = new StringWriter();
            properties.store(stringWriter, null);
            String data = stringWriter.getBuffer().toString();
            storageManager.saveToFile(data);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Retrieves the current state of the application properties from the specified file.
     *
     * @return the current state of the application properties.
     */
    private Properties readStateFromFile() {
        // Set default properties.
        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("language", "en");
        defaultProperties.setProperty("country", "SG");

        Properties properties = new Properties(defaultProperties);
        try {
            // Initialise properties with the values in the config file.
            properties.load(new StringReader(storageManager.readFromFile()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return properties;
    }

    /**
     * Searches for the property with the specified key in this property list. If the key is not found
     * in this property list, the default property list, and its defaults, recursively, are then checked.
     * The method returns {@code null} if the property is not found.
     *
     * @param key the property key.
     * @return the value in the property list with the specified key value.
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Calls the {@code Hashtable} method {@code put}. Provided for parallelism with the {@code getProperty}
     * method. Enforces use of strings for property keys and values. The value returned is the result of the
     * {@code Hashtable} call to put.
     *
     * @param key the key to be placed into the property list.
     * @param value the value corresponding to the key.
     * @return the previous value of the specified key in the property list, or {@code null} if it did not
     *         have one.
     */
    public Object setProperty(String key, String value) {
        Object previousValue = properties.setProperty(key, value);
        syncStateToFile();
        return previousValue;
    }
}
