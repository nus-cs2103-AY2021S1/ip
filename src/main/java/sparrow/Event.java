package sparrow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event in the task list.
 */
public class Event extends Task {
    protected LocalDate date;

    /**
     * Creates an Event.
     * @param description details of the event
     * @param date when the event occurs
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return String.format("[E]%s (at: %s)", super.toString(), this.date.format(formatter));
    }
}
