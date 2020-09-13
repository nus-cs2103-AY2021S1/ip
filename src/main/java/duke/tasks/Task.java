package duke.tasks;

/**
 * Parent Task class with multiple sub-classes that are different types of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create new Task object.
     *
     * @param description specific details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the details of the task.
     *
     * @return details of the tasks.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the particular task is done.
     *
     * @return true or false if task is done.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Get the status icon according to whether task is done or not.
     *
     * @return status icon, a tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

}
