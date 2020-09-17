package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;

/**
 * Represents an Event.
 */
public class Event extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final LocalDateTime datetime;

    /**
     * Initializes an event specifying whether the event is complete.
     *
     * @param content Contents of the event.
     * @param datetimeString Datetime of the event.
     * @param isComplete Completion status of the event.
     * @param priority Priority of the event.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Event(String content, String datetimeString, Boolean isComplete, String priority) throws DukeException {
        super(content, isComplete, priority);
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
     * Initializes an incomplete event.
     *
     * @param content Contents of the event.
     * @param datetimeString Datetime of the event.
     * @param priority Priority of the event.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Event(String content, String datetimeString, String priority) throws DukeException {
        super(content, priority);
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
