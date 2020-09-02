package duke.storage;

public class TaskStorageSideEffects {

    private static TaskStorageSideEffects instance;
    //CHECKSTYLE:OFF: VisibilityModifier
    public boolean getSavedTasks;
    public boolean saveTasks;
    //CHECKSTYLE:ON: VisibilityModifier

    private TaskStorageSideEffects() {
        reset();
    }

    public static TaskStorageSideEffects getInstance() {
        if (instance == null) {
            instance = new TaskStorageSideEffects();
        }

        return instance;
    }

    /**
     * Resets all values to false.
     */
    public void reset() {
        getSavedTasks = false;
        saveTasks = false;
    }
}
