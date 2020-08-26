package duke;

/**
 * Stores a Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a Task.
     *
     * @param description Name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Instatiates a task with completion status.
     *
     * @param description Name of the task.
     * @param isDone      Completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns an visual representation of the completion
     * status of the task.
     *
     * @return Tick or cross symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns the description of the task.
     *
     * @return Name of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return Boolean of completion status.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Sets the completion status of the task to true.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Overrides toString() method.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
