package duke.task;

/**
 * Represents the task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a <code>Task</code> object.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Marks the task as done.
     * @return A new task that has been marked as done
     */
    public Task markAsDone() {
        isDone = true;
        return this;
    }

    /**
     * Represents the done status of the task through icon.
     * @return An icon
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]"; //return tick or X symbols
        //return "[" + (isDone ? "Done" : "Not done") + "]";
    }

    /**
     * Notifies whether this task is done.
     * @return True if this task is done, False otherwise
     */
    public boolean isDone() {
        return this.isDone;    
    }

    /**
     * Gets the description of this task.
     * @return The description of this task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the time which this task happens.
     * @return An empty String if the task is of type Todo, else return the time string input by the user
     */
    public String getTime() {
        return "";
    }

    /**
     * Represents the type of this task through an icon.
     * @return An icon
     */
    public abstract String getTypeIcon();

    /**
     * Converts the task to string.
     * @return The string representation of this task
     */
    @Override
    public abstract String toString();
}
