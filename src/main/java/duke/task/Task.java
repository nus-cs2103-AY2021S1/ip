package duke.task;

import duke.Encodable;
import duke.Searchable;
import duke.exceptions.DukeTaskCreationException;

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
     * @throws DukeTaskCreationException if the {@code description} is empty.
     */
    protected Task(String description) throws DukeTaskCreationException {
        if (description.trim().length() == 0) {
            throw new DukeTaskCreationException("That's really descriptive...");
        }
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
