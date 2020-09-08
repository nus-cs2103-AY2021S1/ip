package tasks;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected Boolean isDone;

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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
