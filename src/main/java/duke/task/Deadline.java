package duke.task;

import duke.task.Task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a deadline item that inherits from Task.
 */
public class Deadline extends Task {

    /** Date of when the deadline is due. */
    protected Date date;
    /** Boolean that represents if the date contains a time. */
    protected boolean isTime;

    /**
     * Creates a deadline with a default not done.
     * @param description Description of deadline.
     * @param date Date of deadline.
     * @param isTime Indicates if date has a time.
     */
    public Deadline(String description, Date date, boolean isTime) {
        super(description);
        this.date = date;
        this.isTime = isTime;
    }

    /**
     * Creates a deadline.
     * @param description Description of deadline.
     * @param date Date of deadline.
     * @param isTime Indicates if date has a time.
     * @param isDone Indicates if deadline is done.
     */
    public Deadline(String description, Date date, boolean isTime, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.isTime = isTime;
    }

    /**
     * {@inheritDoc}
     * @return Deadline as a String.
     */
    @Override
    public String toString() {
        String s = isTime ? "MMM dd yyyy hh:mma" : "MMM dd yyyy";
        return "[D]" + super.toString() + " (by: " + new SimpleDateFormat(s).format(date) + ")";
    }

    /**
     * {@inheritDoc}
     * @return Deadline as a String.
     */
    @Override
    public String toStoredTextString() {
        String s = isTime ? "MMM dd yyyy hh:mma" : "MMM dd yyyy";
        return "D | " + super.toStoredTextString() + " | " + new SimpleDateFormat(s).format(date) + " | " + (isTime ? "1" : "0");
    }
}
