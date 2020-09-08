package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent an event with the time it takes place
 */
public class Event extends Task {

    private final LocalDate happenAt;
    private LocalDate endAt;

    /**
     * @param desc     description for Event
     * @param happenAt the time for the Event to take place
     */
    public Event(String desc, String happenAt) {
        super(desc);
        this.happenAt = LocalDate.parse(happenAt);
    }

    public Event(String desc, String happenAt, String endAt) {
        super(desc);
        this.happenAt = LocalDate.parse(happenAt);
        this.endAt = LocalDate.parse(endAt);
    }

    @Override
    public String toString() {
        if (endAt == null) {
            return "[E]" + super.toString() + " (at: " + happenAt.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[E]" + super.toString() + " (between: " + happenAt.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " and " + endAt.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * @return the representation of the event when written to disk
     */
    @Override
    protected String toDisk() {
        if (endAt == null) {
            return String.format("event\n%s\n%d\n%s", desc, (isDone ? 1 : 0), happenAt);
        }
        return String.format("event\n%s\n%d\n%s\n%s", desc, (isDone ? 1 : 0), happenAt, endAt);

    }
}
