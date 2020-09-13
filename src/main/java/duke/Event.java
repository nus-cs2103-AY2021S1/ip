package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event. A type of Task.
 */
public class Event extends Task {

    /**
     * Creates an Event object with the specified description
     * and time.
     * @param description Description of the Event.
     * @param at Time of the Event.
     * @throws DateTimeParseException If the input time is of the wrong format.
     */
    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDateTime.parse(at);
        this.type = "E";
    }

    @Override
    public String toString() {
    return "[E]" + super.toString() + " (at: " + this.formatDeadline("MMM d yyyy") + ")";
    }

}
