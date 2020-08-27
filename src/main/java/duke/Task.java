package duke;

/**
 * Creates a task which can be further classified into different categories.
 */
public class Task {
    protected String taskname;
    protected boolean status;

    public Task(String taskname, boolean status) {
        this.taskname = taskname;
        this.status = status;
    }

    /**
     * Returns the status icon to indicate whether the task has been done.
     *
     * @return The status icon.
     */
    protected String getStatusIcon() {
        return (status ? "\u2713" : "\u2718");
    }

    /**
     * Marks a certain task as done
     */
    protected void markAsDone() {
        this.status = true;
    }

    /**
     * Returns the string representation of a task
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskname;
    }
}
