package dude.task;

import java.time.LocalDate;

/**
 * The class handles tasks that occurs on a specific date.
 */

public class Event extends DatedTask {
    public Event(String description, LocalDate at) {
        super(description, at);
    }

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone, at);
    }

    @Override
    public String toSave() {
        return "E | " +  super.toSave();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
