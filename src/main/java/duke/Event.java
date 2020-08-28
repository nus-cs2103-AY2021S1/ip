package duke;

import java.time.LocalDate;

/**
 * Represents an {@code Event} object. Inherits from {@code Task} object
 */
class Event extends Task {

    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
