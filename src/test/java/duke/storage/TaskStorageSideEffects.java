package duke.storage;

public class TaskStorageSideEffects {

    public boolean getSavedTasks;

    public boolean saveTasks;

    private TaskStorageSideEffects() {
        reset();
    }

    private static TaskStorageSideEffects instance;
    public static TaskStorageSideEffects getInstance() {
        if (instance == null) {
            instance = new TaskStorageSideEffects();
        }

        return instance;
    }

    public void reset() {
        getSavedTasks = false;
        saveTasks = false;
    }
}
