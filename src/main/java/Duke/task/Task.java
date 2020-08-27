package Duke.task;

/**
 * Represents an Task. It has a description, a done indicator and a String representation
 * for storing it in the storage.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String storeAs;

    /**
     * Creates a new Task Object with a description.
     *
     * @param description Describes the Task Object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the done status of the Task
     *
     * @return done status
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        this.updateDoneForStoreAs();
    }

    private void updateDoneForStoreAs() {
        this.storeAs = this.storeAs.substring(0, 2) + "1" + this.storeAs.substring(3);
    }

    /**
     * Get the String representation of the Task for storage.
     *
     * @return String representation of the Task for storage.
     */
    public String getStoreAs() {
        return this.storeAs;
    }

    /**
     * Get the description of the task.
     *
     * @return String description of the Task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the String representation of the task.
     *
     * @return String representation of the Task
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
