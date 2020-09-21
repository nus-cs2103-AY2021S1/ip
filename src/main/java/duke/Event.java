package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an <code>Event</code> object characterized by a <code>command</code> and a <code>time</code>.
 */
public class Event extends Task {
    /**
     * The time of an event.
     */
    private LocalDateTime time;

    /**
     * Creates a new <code>Event</code> with the given <code>command</code> and <code>time</code>.
     */
    public Event(String command, LocalDateTime time) {
        super(command);
        this.time = time;
    }

    /**
     * Returns the time of an event.
     * @return the time of this event.
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Returns a string representation of an event.
     * @return the string representation of this event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " +
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}