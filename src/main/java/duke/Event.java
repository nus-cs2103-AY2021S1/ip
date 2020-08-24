package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents an Event, a type of Task, in the Duke program.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates an event.
     * @param description the decription of the event
     * @param date the date of the event
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.at = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
            + " (at: " + at.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }
}
