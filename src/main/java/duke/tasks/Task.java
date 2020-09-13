package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that is created upon user input.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    /**
     * Constructor which stores the description of the task
     * and initialises the completion of it to be false.
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.date = null;
    }

    /**
     * Formats the date into a certain pattern.
     * @return the date in string format.
     */
    public String dateFormatted() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string as a tick or a cross.
     * @return String representing a tick or a cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks if the task is completed.
     * @return if the task is completed.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Getter for the description of the task.
     * @return String representation of the description.
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Prints out the error message when the input is invalid.
     */
    public static String invalidInput() {
        return null;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
}
