package duke.task;

import duke.DukeException;
import duke.util.DateTimeHandler;

/**
 * Represents an event as a task with a date and time.
 */
public class Event extends Task {

    private final String dateTime;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.dateTime = DateTimeHandler.parseDateTime(at);
    }

    public Event(String description, boolean isDone, String dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    @Override
    public String getData() {
        return "E / " + super.getData() + " / " + this.getDateTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateTime() + ")";
    }
}
