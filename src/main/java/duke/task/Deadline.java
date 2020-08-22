package main.java.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task associated with a deadline.
 * The instance of the Deadline class has the description and
 * the time of the deadline.
 */
public class Deadline extends Task{
    protected LocalDate by;

    /**
     * Takes in the description and time of a deadline and returns an instance of
     * the corresponding type of task.
     *
     * @param description The description of the deadline.
     * @param by The time of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a representation of the task in terms of the description and state.
     *
     * @return The representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a representation of the task in terms of the description and
     * state for the task to be stored into the record.
     *
     * @return The representation of the task in terms of
     *         the description, the state of completion, and time.
     */
    @Override
    public String record() {
        if(this.isCompleted) {
            return "D | 1 | " + description + " | " + by;
        } else {
            return "D | 0 | " + description + " | " + by;
        }
    }

    /**
     * Returns the boolean value that describes whether the task is on
     * the specific date.
     *
     * @param localDate The specific date.
     * @return The boolean value that describes whether the task is on
     * the specific date.
     */
    @Override
    public boolean isAt(LocalDate localDate) {
        return this.by.equals(localDate);
    }
}
