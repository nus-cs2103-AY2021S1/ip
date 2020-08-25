package duke.task;

/**
 * Encapsulates a task.
 */
public class Task {
    /**
     * The description of the task to be done.
     */
    protected String description;

    /**
     * Whether the task is done.
     */
    protected boolean isDone;

    /**
     * Initialize a new Task object.
     *
     * @param description the details of the task to be done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initialize a new Task object.
     *
     * @param description the details of the task to be done.
     * @param isDone whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a visual representation of whether the task is done or not.
     *
     * @return Tick for done, Cross for not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task in a save friendly format.
     * The way it is saved affects how the .txt file is read in {@link duke.Storage}
     *
     * @return Save-friendly string of the task.
     */
    public String getSaveFormat() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.getDescription());
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Data of the task in string format.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", this.getStatusIcon(), this.description);
    }

}
