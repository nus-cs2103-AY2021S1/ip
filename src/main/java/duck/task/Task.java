package duck.task;

import java.io.Serializable;

/**
 * A general Task class to be extended by other classes.
 * Provides the necessary default implementations of a Task.
 */
public abstract class Task implements Serializable {
    private String description;
    private boolean isDone;

    /**
     * Initializes the description and sets done to false.
     *
     * @param description Description of Task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    /**
     * Gets the status of a Task which displays information about
     * whether the task is done and its description.
     *
     * @return Status of task.
     */
    public String getStatus() {
        String check = isDone ? "\u2713" : "\u2718";
        String status = "[" + check + "] " + getDescription();
        return status;
    }
}
