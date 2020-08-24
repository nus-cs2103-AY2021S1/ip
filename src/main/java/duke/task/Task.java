package duke.task;

/**
 * Represents a task to be completed.
 */
public abstract class Task {
    /** Description of the task. */
    protected String description;
    /** Marks if the task is done or not. */
    protected boolean isDone;

    /**
     * Creates a new task with the specified description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon.
     * "✓" indicates this task is completed and "✘" indicates this task is not completed.
     * @return A string of the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    /**
     * If this task is done, returns true, otherwise false.
     * @return True, if this task is done, otherwise false.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets the description of this task.
     * @return The description of this task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the short form of this task.
     * @return The short form of this task.
     */
    public abstract String getShortForm();

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of this task.
     * @return A string representation of this task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
