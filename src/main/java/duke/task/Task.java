package duke.task;

/**
 * An abstract class that represents a
 * basic Task.
 */
public abstract class Task {

    protected final String description;
    protected final boolean isDone;

    /**
     * Initializes a Task object.
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]";
    }

    /**
     * Gets the raw data representation of the Task.
     * @return The raw data representation of the Task.
     */
    public String getData() {
        return (isDone ? "1" : "0") + "|" + description;
    }

    /**
     * Mark the Task as done.
     * @return The Task after it is marked as done.
     */
    public abstract Task markAsDone();

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
