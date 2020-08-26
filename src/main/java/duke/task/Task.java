package duke.task;

import duke.Encodable;

/**
 * The {@code Task} class provides a skeletal implementation of an object representing a task.
 * Implements the {@code Encodable} interface.
 */
public abstract class Task implements Encodable<Task> {

    protected String description;
    protected boolean completed;

    /**
     * Constructs an instance of a task.
     *
     * @param description the description of the task.
     */
    protected Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Marks a task as complete.
     */
    public void setCompleted() {
        this.completed = true;
    }

    private String getStatusIcon() {
        return this.completed ? "\u2713" : "\u2718";
    }

    /**
     * Returns a string representation of this {@code Task}.
     * Contains the description and completion status of this {@code Task}.
     *
     * @return a string representation of this {@code Task}.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
