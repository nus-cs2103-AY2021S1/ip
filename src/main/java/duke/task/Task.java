package duke.task;

/**
 * Represents a task to be completed.
 */
public class Task {
    /** The task description */
    protected String description;
    /** Indicates whether the task is completed or not */
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description.
     * A newly created task is always initially set as "not done".
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description.strip();
        this.isDone = false;
    }

    /**
     * Retrieves the status icon which shows whether the task is done or not.
     *
     * @return '1' if the task is done or '0' if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the description and status of the task in a particular string format.
     * This format is used for saving tasks, i.e. 'saved' format.
     *
     * @return a string representation of the task in a 'saved' format.
     */
    public String saveAs() {
        return isDone + " | " + description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return description;
    }
}
