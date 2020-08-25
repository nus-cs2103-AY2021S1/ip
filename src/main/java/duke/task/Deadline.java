package main.java.duke.task;

import java.time.LocalDate;

/**
 * Encapsulates a deadline
 */
public class Deadline extends Task {

    /**
     * Constructor
     *
     * @param description Description of the deadline
     * @param isComplete Completion status of the task
     * @param date Date of the deadline
     */
    public Deadline(String description, boolean isComplete, LocalDate date) {
        super(description, isComplete, date);
    }

    /**
     * Gets the string representation of the deadline to be written into the file upon exit
     *
     * @return String representation of the deadline in the file
     */
    @Override
    public String[] getDataString() {
        return new String[] {"deadline", String.valueOf(this.isComplete), this.description, this.date.toString()};
    }

    /**
     * Gets the string representation of the deadline to be printed in the UI
     *
     * @return String representation of the deadline in the UI
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + super.getDateString() + ")";
    }
}
