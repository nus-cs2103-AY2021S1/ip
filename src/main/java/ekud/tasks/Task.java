package ekud.tasks;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description of the task as a <code>String</code>
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Copy the current task in a deep manner.
     *
     * @return the copy of the task
     */
    public Task copy() {
        Task ret = new Task(description);
        ret.isDone = isDone;

        return ret;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
