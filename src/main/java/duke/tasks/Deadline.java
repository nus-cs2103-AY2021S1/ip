package duke.tasks;

import duke.exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline.
 */
public class Deadline extends Task {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime datetimeDue;

    /**
     * Class constructor specifying whether the deadline is complete.
     *
     * @param content           the contents of the deadline
     * @param datetimeDueString the due datetime of the deadline
     * @param isComplete        the completion status of the deadline
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Deadline(String content, String datetimeDueString, boolean isComplete) throws DukeException {
        super(content, isComplete);
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
     * Class constructor.
     *
     * @param content           the contents of the deadline
     * @param datetimeDueString the due datetime of the deadline
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Deadline(String content, String datetimeDueString) throws DukeException {
        super(content);
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
     * @return user-readable deadline string
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), datetimeDue.format(formatter));
    }

    /**
     * Returns a deadline string readable by storage.
     *
     * @return storage-safe deadline string
     */
    @Override
    public String toSaveString() {
        return String.format("D/%s/%s", super.toSaveString(), datetimeDue.format(formatter));
    }
}
