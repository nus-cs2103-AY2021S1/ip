package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an Event object in Duke.
 */
public class Event extends Task {
    protected LocalDateTime at;

    public Event(String task, LocalDateTime at) {
        super(task);
        this.at = at;
    }

    public Event(String task, String at, boolean isDone) {
        super(task, isDone);
        LocalDateTime dateTime = LocalDateTime.parse(at, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.at = dateTime;
    }

    public LocalDateTime checkAt() {
        return this.at;
    }

    @Override
    public String toString() {
        String dateTime = at.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
