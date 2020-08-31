package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent an event with the time it takes place
 */
public class Event extends Task {

    private final LocalDate happenAt;

    /**
     * @param desc     description for Event
     * @param happenAt the time for the Event to take place
     */
    public Event(String desc, String happenAt) {
        super(desc);
        this.happenAt = LocalDate.parse(happenAt);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + happenAt.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * @return the representation of the event when written to disk
     */
    @Override
    protected String toDisk() {
        return String.format("event\n%s\n%d\n%s", desc, (isDone ? 1 : 0), happenAt);
    }
}
