/**
 * Wrapper class for tasks.
 */
package sg.christopher.duke.entities;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets a tick or cross representation of the done status of the task.
     *
     * @return tick or cross icon representation of the done status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Gets the task's description.
     *
     * @return the task's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}