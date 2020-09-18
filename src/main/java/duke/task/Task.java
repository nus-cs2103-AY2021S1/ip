package duke.task;

/**
 * Creates a task.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a task
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates the message to be written into the local file.
     *
     * @return A string representation of the task in the local file.
     */
    public String writeMessage() {
        return "";
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "✓" : "✗") + "] " + this.description;
    }
}
