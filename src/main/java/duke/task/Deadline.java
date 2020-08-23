package main.java.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime localDateTime;
    protected String date;

    private DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .appendPattern("[d-MM-yyyy HHmm][yyyy-MM-d HHmm]")
            .optionalStart()
            .appendPattern(" HHmm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    /**
     * Creates a new instance of a Deadline.
     *
     * @param description Description of the task with deadline.
     * @param by Date of the task's deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.date = by;
        by = by.replace('/', '-');
        this.localDateTime = LocalDateTime.parse(by, fmt);
    }

    /**
     * Gets deadline of task.
     *
     * @return Date of task.
     */
    public String getDate() {
        return date;
    }

    /**
     * Converts a Deadline object to a string.
     *
     * @return A string displaying the task and its status.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}
