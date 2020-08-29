package duke.tasks;

import duke.exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event.
 */
public class Event extends Task {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime datetime;

    /**
     * Class constructor specifying whether the event is complete.
     *
     * @param content        Contents of the event.
     * @param datetimeString Datetime of the event.
     * @param isComplete     Completion status of the event.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Event(String content, String datetimeString, Boolean isComplete) throws DukeException {
        super(content, isComplete);
        if (datetimeString.replace(" ", "").equals("")) {
            throw new DukeException("Event datetime cannot be empty.");
        }
        try {
            this.datetime = LocalDateTime.parse(datetimeString, formatter);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid datetime specified (Needs to be yyyy-MM-dd HH:mm).");
        }
    }

    /**
     * Class constructor.
     *
     * @param content        Contents of the event.
     * @param datetimeString Datetime of the event.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Event(String content, String datetimeString) throws DukeException {
        super(content);
        if (datetimeString.replace(" ", "").equals("")) {
            throw new DukeException("Event datetime cannot be empty.");
        }
        try {
            this.datetime = LocalDateTime.parse(datetimeString, formatter);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid datetime specified (Needs to be yyyy-MM-dd HH:mm).");
        }
    }

    /**
     * Returns a user-readable event string.
     *
     * @return User-readable event string.
     */
    @Override
    public String toString() {

        return String.format("[E]%s (at: %s)", super.toString(), datetime.format(formatter));
    }

    /**
     * Returns an event string readable by storage.
     *
     * @return Storage-safe event string.
     */
    @Override
    public String toSaveString() {
        return String.format("E/%s/%s", super.toSaveString(), datetime.format(formatter));
    }
}
