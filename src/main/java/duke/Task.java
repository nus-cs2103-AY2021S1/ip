package duke;

import java.time.LocalDate;

/**
 * The task base class. Has a description and indicator whether the task is done or not.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Task constructor.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Task constructor with specified isDone.
     *
     * @param description the description of the task.
     * @param isDone      specify whether the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon.
     *
     * @return return the icon according to the is done status.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Updates the description.
     *
     * @param description updated description.
     */
    public void updateDescription(String description) {
        this.description = description;
    }

    /**
     * Updates the time.
     *
     * @param time updated time.
     */
    public void updateTime(LocalDate time) throws DukeException {
        throw new DukeException("The task doesn't have a time property.");
    }

    /**
     * Gets the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Formats task to be written to a file.
     *
     * @return formatted string of the task.
     */
    public String writeToFile() {
        return String.format("%b | %s", isDone, description);
    }
}
