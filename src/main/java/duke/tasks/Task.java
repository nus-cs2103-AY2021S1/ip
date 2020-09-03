package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a type, a description, a status, and a date.
 * @version 1.0
 */
public class Task {
    private final TaskType type;
    private final String description;
    private boolean isDone;
    private final LocalDate date;

    /**
     * Creates a new Task object with the given type, description.
     * Assigns a date if it is a type of deadline or event.
     *
     * @param type The type of the task.
     * @param description A String representation of the description of the task.
     * @param date The date of deadline or event.
     */
    public Task(TaskType type, String description, LocalDate... date) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        this.date = date.length > 0 ? date[0] : null;
    }

    /**
     * Returns the type of the task.
     *
     * @return the type of the task.
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task.
     *
     * @return the status of the task.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return the status icon of the task.
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the date of a deadline or event.
     *
     * @return the date of a deadline or event.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the task status to done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns if a task has a date information.
     *
     * @return if a task has a date information.
     */
    public boolean hasDate() {
        return date != null;
    }

    /**
     * Returns The String representation of a task.
     *
     * @return the String representation of a task.
     */
    @Override
    public String toString() {
        String string = "[";
        switch (type) {
        case TODO:
            string += "T][" + getStatusIcon() + "] " + getDescription();
            break;
        case EVENT:
            string += "E][" + getStatusIcon() + "] " + getDescription()
                    + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            break;
        case DEADLINE:
            string += "D][" + getStatusIcon() + "] " + getDescription()
                    + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            break;
        default:
        }
        return string;
    }
}
