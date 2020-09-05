package nite.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nite.exception.NiteException;

/**
 * Represents a Task which is a Deadline.
 */
public class Deadline extends Task {

    private LocalDateTime timeBy;

    /**
     * Creates a Deadline Task.
     *
     * @param description Description of Deadline.
     * @param timeByString The date and time which Deadline is due.
     * @throws NiteException If format of time is wrong.
     */
    public Deadline(String description, String timeByString) throws NiteException {
        super(description);
        LocalDateTime timeBy;
        try {
            timeBy = LocalDateTime.parse(timeByString, super.inputDateTimePattern);
        } catch (DateTimeParseException ignored) {
            throw new NiteException("Wrong format of for deadline time.");
        }
        this.timeBy = timeBy;
    }

    /**
     * Returns the String representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        String timeBy = this.timeBy.format(super.displayDateTimePattern);
        return "[D]" + super.toString() + " (by: " + timeBy + ")";
    }

    /**
     * Returns the text representation of a Deadline to be saved in a text file.
     *
     * @return Text representation of Deadline.
     */
    @Override
    public String toData() {
        String isDone = super.isDone ? "1" : "0";
        String separator = "~";
        String timeBy = this.timeBy.format(super.inputDateTimePattern);
        return String.format("D%s%s%s%s%s%s%n", separator, isDone, separator,
                super.description, separator, timeBy);
    }
}
