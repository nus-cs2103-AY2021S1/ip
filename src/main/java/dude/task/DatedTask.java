package dude.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class handles Tasks that contains a date parameter.
 */

public class DatedTask extends Task {
    protected LocalDate date;

    /**
     * Constructor for the DatedTask class.
     *
     * @param description description of the task.
     * @param date date attached to the task.
     */
    public DatedTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructor for the DatedTask class.
     *
     * @param description description of the task.
     * @param isDone boolean value to denote if a task is completed.
     * @param date date attached to the task.
     */
    public DatedTask(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Returns a string representation of the task to be written into the data file.
     *
     * @return String formatted description.
     */
    @Override
    public String toSave() {
        return super.toSave() + " | " + date;
    }

    /**
     * Returns a string representation of the task to be displayed to the user.
     *
     * @return String formatted description.
     */
    @Override
    public String toString() {
        return super.toString() + " (date: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

