package duke.task;

/**
 * Encapsulates a task with a description and a completion status.
 */
public class Task {

    /** Description of the task. */
    private final String description;

    /** Completion status of the task. */
    private boolean isDone;

    /**
     * Creates a new task from a description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new task from a description and completion status.
     *
     * @param description Description of the task.
     * @param isDone Completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task using UTF-8 encoding.
     * Either a tick or a cross symbol.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets data from the task to be saved in storage.
     *
     * @return String representing the data of the task.
     */
    public String getData() {
        int statusNumber = this.getStatusIcon().equals("\u2713")
                ? 1
                : 0;
        return statusNumber + " / " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
