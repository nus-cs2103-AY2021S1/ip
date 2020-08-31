package duke.task;

/**
 * Represents tasks which user enter into the app.
 */
public abstract class Task {

    protected boolean isCompleted;
    protected String description;
    protected char symbol = 'T';

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public Task(String description, boolean completed) {
        this.description = description;
        this.isCompleted = completed;
    }

    /**
     * Marks a Task as completed.
     *
     * @return new Task object which has isCompleted set to true
     */
    public abstract Task markCompleted();

    /**
     * Gets a String representation of the Task that is used for storage.
     *
     * @return a String representation of the Task for storage
     */
    public String getStorageString() {
        char done = isCompleted ? '1' : '0';
        return String.format("%s | %s | %s", symbol, done, description);
    }

    @Override
    public String toString() {
        String completionStatus = isCompleted ? "\u2713" : "\u2718";
        return String.format("[%s] %s", completionStatus, description);
    }
}
