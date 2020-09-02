package src.main.java.duke.data.task;

import java.util.Objects;

/**
 * The task class that represents a task.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {}

    /**
     * Costructor for task with description
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     * @return String of status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * This method marks the task as done.
     * @return true value
     */
    public boolean markAsDone() {
        isDone = true;
        return true;
    }

    /**
     * Marks the task as done.
     * @return true value
     */
    public boolean markAsDoneWithoutPrint() {
        isDone = true;
        return true;
    }

    /**
     * Returns the hashCode of the task.
     * @return integer which represents the hashcode
     */
    public int hashCode() {
        return this.description.length();
    }

    /**
     * Gets the string to be printed for the task.
     * @return string to be printed for the task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Checks if the object is equal to task.
     * @return true if it is equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone
                && Objects.equals(description, task.description);
    }

    /**
     * Gets a string that is to be written.
     * @return String that is to be written
     */
    public String toWriteString() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
