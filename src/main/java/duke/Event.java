package duke;

import java.time.LocalDate;

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
        this.happenAt = Parser.parseDate(happenAt);
    }

    /**
     * @param desc     description of the event
     * @param happenAt the time that it happens
     * @param endAt    the time that it ends
     */
    public Event(String desc, String happenAt, String endAt) {
        super(desc);
        this.happenAt = Parser.parseDate(happenAt);
        this.endAt = Parser.parseDate(endAt);
    }

    @Override
    public String toString() {
        if (endAt == null) {
            return "[E]" + super.toString() + " (at: " + dateToString(happenAt) + ")";
        }
        return "[E]" + super.toString() + " (between: " + dateToString(happenAt)
            + " and " + dateToString(endAt) + ")";
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
