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
}
