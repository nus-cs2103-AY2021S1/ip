package Tasks;

/**
 * Create Task objects.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets status Icon.
     * @return status Icon.
     */
    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Gets Task description.
     * @return task description.
     */
    protected String getDescription() {
        return this.description;
    }

    /**
     * Create a new Task object with isDone equal true.
     * @return new Task object.
     */
    protected Task markAsDone() {
        return new Task(this.description, true);
    }

    /**
     * Gets isDone status.
     * @return boolean.
     */
    protected boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}