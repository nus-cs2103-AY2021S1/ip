package duke.task;

import java.time.LocalDate;

/**
 * Encapsulates methods and information relating to a task.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates and initialises a new task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon to indicate the completion status of this task.
     *
     * @return Tick symbol if the task is completed, else a cross symbol is returned.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✗");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts the task object into a string for storage in a file.
     *
     * @return String containing the relevant information of this task object to be saved in a file.
     */
    public abstract String taskFileString();

    /**
     * Returns the date of this task.
     *
     * @return LocalDate object that stores the date of the task.
     */
    public abstract LocalDate getDate();

    /**
     * Returns a boolean to indicate whether the task has been completed.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return this.isDone;
    }

    /**
     * Converts the task object into a string to be displayed.
     *
     * @return String representation of this task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
