package duke;
/**
 * Is responsible for basic functionality of task.
 * They are different types of task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon for task for indicating whether task is done or not.
     *
     * @return status icon indicating whether task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns string representation of task in string format.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
