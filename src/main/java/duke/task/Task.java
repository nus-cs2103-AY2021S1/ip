package duke.task;

/**
 * Represents an Task. It has a description, a done indicator and a String representation
 * for storing it in the storage.
 */
public class Task {
    protected static final String SEPARATOR = ",";
    protected static final String DONE = "1";
    protected static final String NOT_DONE = "0";
    protected static final String TICK_SYMBOL = "\u2713";
    protected static final String CROSS_SYMBOL = "\u2718";
    protected static final String DATE_TIME_INPUT_PATTERN = "yyyy-MM-dd HH:mm";
    protected static final String DATE_TIME_OUTPUT_PATTERN = "MMM d yyyy HH:mm";
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
        //return tick or X symbols
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL);
    }

    /**
     * Mark the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        this.updateDoneForStoreAs();
    }

    private void updateDoneForStoreAs() {
        String taskType = this.storeAs.substring(0, 2);
        String taskDescription = this.storeAs.substring(3);
        this.storeAs = taskType + DONE + taskDescription;
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
