package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task which is a Deadline.
 */
public class Deadline extends Task {

    private LocalDateTime timeBy;

    /**
     * Creates a Deadline Task.
     *
     * @param description Description of Deadline.
     * @param by The date and time which Deadline is due.
     * @throws DukeException If format of time is wrong.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        LocalDateTime timeBy;
        try {
            timeBy = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException ignored) {
            throw new DukeException("Wrong format of for deadline time.");
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
        String timeBy = this.timeBy.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
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
        String timeBy = this.timeBy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format("D%s%s%s%s%s%s%n", separator, isDone, separator,
                super.description, separator, timeBy);
    }
}
