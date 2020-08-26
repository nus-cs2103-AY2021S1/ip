package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task which is a Deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime time;

    /**
     * Creates a Deadline Task.
     *
     * @param description Description of Deadline.
     * @param by The date and time which Deadline is due.
     */
    public Deadline(String description, String by) {
        super(description);
        LocalDateTime timeBy;
        try {
            timeBy = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException ignored) {
            return;
        }
        this.time = timeBy;
    }

    /**
     * Returns the String representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        String by = time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + by + ")";
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
        String by = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "D" + separator + isDone + separator + super.description + separator + by + "\n";
    }
}