package duke.task;

import java.time.LocalDate;

/**
 * The class that represents a task.
 */
public class Task {
    /**
     * Keeps track of how many tasks have been created.
     */
    protected static int numOfTasks;

    /**
     * The description of the Task.
     */
    protected String description;

    /**
     * A boolean value to indicate if the task is completed.
     */
    protected boolean isCompleted;

    /**
     * The id of a Task.
     */
    protected final int id;

    /**
     * Instantiates a new Task.
     * Assigns its id to be the current number of Tasks + 1.
     * @param description The description fo the task.
     */
    Task(String description) {
        this.description = description;
        this.isCompleted = false;
        this.id = numOfTasks++;
    }

    /**
     * Instantiates a new Task based on the completion status given.
     * Assigns its id to be the current number of Tasks + 1.
     * @param description The description of the Task.
     * @param completionStatus The completion status of the Task.
     */
    Task(String description, String completionStatus) {
        this.description = description;
        this.isCompleted = completionStatus.equals("1");
        this.id = numOfTasks++;
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
     * Returns the id of the Task.
     * @return The id of the Task.
     */
    int getId() {
        return id;
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
        return (getId() + " | " + getType() + " | " + (isCompleted ? "1" : "0") + " | " + description + (getDate() != null ? (" | " + getDate()) : ""));
    }

    public boolean includesKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
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
