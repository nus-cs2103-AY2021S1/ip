package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;

/**
 * Represents a Deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final LocalDateTime datetimeDue;

    /**
     * Initializes a deadline specifying whether the deadline is complete.
     *
     * @param content Contents of the deadline.
     * @param datetimeDueString Due datetime of the deadline.
     * @param isComplete Completion status of the deadline.
     * @param priority Priority of the deadline.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Deadline(String content, String datetimeDueString,
                    boolean isComplete, String priority) throws DukeException {
        super(content, isComplete, priority);
        if (datetimeDueString.replace(" ", "").equals("")) {
            throw new DukeException("Event datetime cannot be empty.");
        }
        try {
            this.datetimeDue = LocalDateTime.parse(datetimeDueString, formatter);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid datetime specified (Needs to be yyyy-MM-dd HH:mm).");
        }
    }


    /**
     * Initializes an incomplete deadline.
     *
     * @param content Contents of the deadline.
     * @param datetimeDueString Due datetime of the deadline.
     * @param priority Priority of the deadline.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Deadline(String content, String datetimeDueString, String priority) throws DukeException {
        super(content, priority);
        if (datetimeDueString.replace(" ", "").equals("")) {
            throw new DukeException("Event datetime cannot be empty.");
        }
        try {
            this.datetimeDue = LocalDateTime.parse(datetimeDueString, formatter);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid datetime specified (Needs to be yyyy-MM-dd HH:mm).");
        }
    }

    /**
     * Returns a user-readable deadline string.
     *
     * @return User-readable deadline string.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), datetimeDue.format(formatter));
    }

    /**
     * Returns a deadline string readable by storage.
     *
     * @return Storage-safe deadline string.
     */
    @Override
    public String toSaveString() {
        return String.format("D/%s/%s", super.toSaveString(), datetimeDue.format(formatter));
    }
}
