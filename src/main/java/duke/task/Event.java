package duke.task;

import duke.exception.DukeException;
import duke.util.DateTime;

/**
 * Class representing an event.
 */
public class Event extends Task {

    /**
     * Creates a brand new {@code Event}.
     *
     * @param description Description of the event.
     * @param at Time that the event is happening at.
     */
    public Event(String description, DateTime at) {
        super(description);
        this.dateTime = at;
        this.taskType = "E";
    }

    private Event(String[] data) throws DukeException {
        super(data);
    }

    /**
     * Loads and initializes a {@code Event} with pre-existing data.
     *
     * @param data Event data.
     * @return A Event task containing the data provided.
     * @throws DukeException If data provided is of an invalid form.
     */
    public static Event loadFromData(String[] data) throws DukeException {
        return new Event(data);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), getDateTime());
    }
}
