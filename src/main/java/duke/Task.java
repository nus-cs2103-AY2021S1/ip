package duke;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a Task in the Duke program.
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task with a specific description.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     * @return the description of this task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the done-ness of the task.
     * @param done the done-ness to be applied on the task.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the status icon of the task, to identify whether it has been done.
     * @return Y if the task has been done, X otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "X"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone &&
            Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }
}
