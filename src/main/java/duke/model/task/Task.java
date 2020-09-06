package duke.model.task;

/**
 * Abstract class for the different types of Tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Tasks input by user.
     *
     * @param description Description of task input by user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Tasks loaded from memory.
     *
     * @param description Description of task.
     * @param isDone Current state of Ttsk.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the relevant status icon depending on whether Task is done.
     *
     * @return Tick if Task is completed, and cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a Task as compeleted.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns whether Task is completed.
     *
     * @return True if Task is completed, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns description of Task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Abstract method to generate String with relevant dat to save to disk.
     *
     * @return String that contains data to save.
     */
    public abstract String toDataString();
}
