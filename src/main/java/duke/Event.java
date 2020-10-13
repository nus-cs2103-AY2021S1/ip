package duke;

import java.time.LocalDate;

/**
 * Event is a form of TimedTask and it contains a description, status and date.
 */
public class Event extends TimedTask {

    public Event(String description, LocalDate date) {
        super(description, date);
    }

    public Event(String description, LocalDate date, boolean isDone) {
        super(description, isDone, date);
    }

    @Override
    public String toText() {
        return super.toText("E");
    }

    @Override
    public String toString() {
        return super.toString("E");
    }
}
