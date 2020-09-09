package duke;

/**
 * Creates a task which can be further classified into different categories.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon to indicate whether the task has been done.
     *
     * @return The status icon.
     */
    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks a certain task as done
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of a task
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}
