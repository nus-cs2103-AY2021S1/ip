package gel.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A type of <code>Task</code> with a date and time description of when the event is heppening.
 */
public class Event extends Task {

    protected String atString;
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.atString = at.format(DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm"));
        this.at = at;
    }

    public LocalDateTime getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atString + ")";
    }
}
