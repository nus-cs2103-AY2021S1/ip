package duke.data.task;

/**
 * Represents a task that has a description and
 * can be completed.
 */
public class Task {
    protected String description;
    private boolean isDone;

    /**
     * Constructor for Task, initialised with a description
     * and not completed.
     * @param description The description describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the corresponding unicode symbol based
     * on whether the task is completed.
     * @return String of either a checkmark or cross depending on whether
     * the task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Getter for isDone.
     * @return boolean of the status of the task.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Getter for description, returns the description of the
     * task as a String.
     * @return String of task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets isDone as true to mark the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("|[%s]|%s", getStatusIcon(), description);
    }
}
