package duke.task;

import java.io.Serializable;

/**
 * A class for different types of class.
 */
public class Task implements Serializable {

    protected String description;
    protected boolean isDone;

    /**
     * Public constructor for a task.
     *
     * It takes a description as argument as all valid tasks
     * should have a description. The task is set to be undone
     * by default.
     *
     * @param description A string describing the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon
     * @return Status icon in unicode
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * If invoked, the task will be marked as done
     * by setting the isDone flag to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
