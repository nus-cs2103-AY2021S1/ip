package duke.utils;

import duke.tasks.TaskManager;

/**
 * State container for the <i>Duke</i> application.
 */
public class Store {
    /** {@code TaskManager} object to keep track of tasks. */
    private static final TaskManager taskManager = new TaskManager();
    /** {@code AliasManager} object to keep track of aliases. */
    private static final AliasManager aliasManager = new AliasManager();
    /** {@code ResourceHandler} object to retrieve string resources. */
    private static final ResourceHandler resourceHandler = new ResourceHandler();
    /** {@code ConfigManager} object to manage the configuration of the application. */
    private static final ConfigManager configManager = new ConfigManager("./data/config.txt");

    /**
     * Constructs a new {@code Store} object. This constructor is private to prevent instantiation.
     */
    private Store() {}

    /**
     * Returns the {@code TaskManager} object that is keeping track of tasks.
     *
     * @return the {@code TaskManager} object that is keeping track of tasks.
     */
    public static TaskManager getTaskManager() {
        return taskManager;
    }

    /**
     * Returns the {@code AliasManager} object that is keeping track of aliases.
     *
     * @return the {@code AliasManager} object that is keeping track of aliases.
     */
    public static AliasManager getAliasManager() {
        return aliasManager;
    }

    /**
     * Returns the {@code ResourceHandler} object for retrieving string resources.
     *
     * @return the {@code ResourceHandler} object for retrieving string resources.
     */
    public static ResourceHandler getResourceHandler() {
        return resourceHandler;
    }

    /**
     * Returns the {@code ConfigManager} object that is managing the configuration of the application.
     *
     * @return the {@code ConfigManager} object that is managing the configuration of the application.
     */
    public static ConfigManager getConfigManager() {
        return configManager;
    }
}
