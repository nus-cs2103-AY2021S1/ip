package duckie.task;

import java.time.LocalDate;

/**
 * Parent class of all Tasks type
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiate a Task object
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Check the task and mark as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Check if a Task is completed or not
     * @return Boolean value indicating the completeness of the task
     */
    public boolean isCompleted() {
        return this.isDone;
    }

    /**
     * Return the description of a Task
     * @return String description of a Task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Return the type of a Task
     * @return null
     */
    public String getType() {
        return null;
    }

    /**
     * Return the date of a Task
     * @return null
     */
    public String getDate() {
        return null;
    }

    /**
     * Overrided method to return the String representation of a Task
     * @return String representation of a Task
     */
    @Override
    public String toString() {
        String tick = "✔";
        String cross = "✘";
        if (isDone) {
            return "[" + tick + "] " + this.description;
        } else {
            return "[" + cross + "] " + this.description;
        }
    }
}
