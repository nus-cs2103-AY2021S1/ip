package duke.task;

/**
 * Represents a user task.
 */
public abstract class Task {
    protected String task;
    protected boolean isDone;

    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    Task(String task, boolean done) {
        this.task = task;
        this.isDone = done;
    }

    /**
     * Gets the task description.
     *
     * @return String of task description.
     */
    public String getTaskDescription() {
        return task;
    }

    /**
     * Returns a String representation of the task to be stored in the
     * storage file.
     *
     * @return Formatted String representing the task.
     */
    public abstract String toDataString();

    /**
     * Sets the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns a String representation of the task status.
     *
     * @return A tick if done, a cross otherwise.
     */
    public String getStatusToString() {
        return isDone ? "\u2713" : "\u2718";
    }

    /**
     * Returns a String representation of the task for display.
     *
     * @return String representation of the task for display.
     */
    @Override
    public String toString() {
        return "[" + getStatusToString() + "] " + task;
    }
}
