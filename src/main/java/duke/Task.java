package duke;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with description.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with description and state of it.
     *
     * @param description Description of task.
     * @param isDone State of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns status of task.
     * @return Status of task.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns description of task.
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
