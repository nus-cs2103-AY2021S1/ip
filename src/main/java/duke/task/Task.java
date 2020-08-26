package duke.task;

import duke.Encodable;
import duke.Searchable;

/**
 * The {@code Task} class provides a skeletal implementation of an object representing a task.
 * Implements the {@code Encodable} and {@code Searchable} interface.
 */
public abstract class Task implements Encodable<Task>, Searchable {

    protected String description;
    protected boolean completed;

    /**
     * Constructs an instance of a task.
     *
     * @param description the description of the task.
     */
    protected Task(String description) {
        this.description = description;
        completed = false;
    }

    /**
     * Marks a task as complete.
     */
    public void setCompleted() {
        completed = true;
    }

    private String getStatusIcon() {
        return completed ? "\u2713" : "\u2718";
    }

    /**
     * Returns a string representation of this {@code Task}.
     * Contains the description and completion status of this {@code Task}.
     *
     * @return a string representation of this {@code Task}.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
