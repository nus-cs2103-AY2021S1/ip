package duke.task;

/**
 * This class represents a Task that the user wants to add/remove/complete/etc. in his todo list.
 */
public class Task {
    private final String taskName;
    private boolean isDone = false;

    /**
     * Initialises a Task with the given name
     * @param taskName Name for the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Gets the name of the Task
     * @return Task's name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Tell's whether the user has completed the Task
     * @return boolean representing whether the Task is completed
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Completes the Task
     */
    public void doTask() {
        isDone = true;
    }

    /**
     * Uncompletes the Task
     */
    public void undoTask() {
        isDone = false;
    }

    /**
     * Gets a String which represents the Task in the appropriate format for storing to the hard disk
     * @return String in the storage format representing Task
     */
    public String getStorageFormat() {
        String done = isDone ? "1" : "0";
        return done + " | " + taskName;
    }

    /**
     * Provides string representation of Task, used for UI display
     * @return String representation of Task
     */
    @Override
    public String toString() {
        final String CHECKMARK = "[✓]";
        final String CROSS = "[✗]";
        return isDone
                ? CHECKMARK + " " + taskName
                : CROSS + " " + taskName;
    }
}
