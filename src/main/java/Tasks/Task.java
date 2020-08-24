package tasks;

/**
 * Create Task objects.
 */
public class Task {
    /** Task's description. */
    protected String description;
    /** Task status. */
    protected boolean isDone;

    /**
     * Constructs Deadline object with Task's description.
     *
     * @param description Task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs Deadline object with Task's description and Task's status.
     *
     * @param description Task's description.
     * @param isDone Task's status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets status Icon.
     *
     * @return Status Icon.
     */
    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Gets Task's description.
     *
     * @return Task's description.
     */
    protected String getDescription() {
        return this.description;
    }

    /**
     * Create a new Task object with isDone equal true.
     *
     * @return New Task object.
     */
    protected Task markAsDone() {
        return new Task(this.description, true);
    }

    /**
     * Gets isDone status.
     *
     * @return Task's status.
     */
    protected boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}