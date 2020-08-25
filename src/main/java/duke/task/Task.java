package duke.task;

import java.time.LocalDate;

/**
 * The class that represents a task.
 */
public class Task {
    /**
     * The description of the Task.
     */
    protected String description;

    /**
     * A boolean value to indicate if the task is completed.
     */
    protected boolean isCompleted;

    /**
     * Instantiates a new Task.
     * @param description The description fo the task.
     */
    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Instantiates a new Task based on the completion status given.
     * @param description The description of the Task.
     * @param completionStatus The completion status of the Task.
     */
    Task(String description, String completionStatus) {
        this.description = description;
        this.isCompleted = completionStatus.equals("1");
    }

    /**
     * Returns a tick: <code>✓</code> if the Task is completed, else a cross <code>✘</code>
     * @return An icon to indicate whether the Task is completed.
     */
    String getStatusIcon() {
        return (isCompleted ? "\u2713" : "\u2718");
    }

    /**
     * Returns the type of the Task.
     * @return The type of the Task.
     */
    String getType() {
        return null;
    }

    /**
     * Returns the date of the Task.
     * @return The date of the Task.
     */
    LocalDate getDate() {
        return null;
    }

    /**
     * Marks the Task as completed.
     */
    void markAsDone() {
        isCompleted = true;
    }

    /**
     * Encodes the Task to a String that will be saved in the storage.
     * @return A String encoding of the Task.
     */
    public String encode() {
        return (getType() + " | " + (isCompleted ? "1" : "0") + " | " + description + (getDate() != null ? (" | " + getDate()) : ""));
    }

    /**
     * Returns a String representation of the Task.
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
