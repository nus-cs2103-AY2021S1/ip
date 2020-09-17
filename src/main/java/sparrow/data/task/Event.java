package sparrow.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event in the task list.
 */
public class Event extends Task {
    protected LocalDate date;

    /**
     * Creates an Event.
     * @param description Details of the event.
     * @param date When the event occurs.
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

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Event
                && description.equals(((Event) other).description)
                && date.equals(((Event) other).date));
    }
}
