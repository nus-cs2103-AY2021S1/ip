package main.java;

import java.time.format.DateTimeFormatter;

/**
 * Deadlines are tasks that need to be done before a specific date/time
 *
 * @author Lio
 */
class Deadline extends Task {
    /**
     * Constructor
     *
     * @param name Name of the task
     * @param by Date that the task is due
     */
    public Deadline(String name, String by) {
        super(name, by);
    }

    /**
     * Converts the task to data form
     */
    public String toData() {
        return "D | " + super.toData() + " | " + this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
