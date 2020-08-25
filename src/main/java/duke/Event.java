package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event with a specified description, date, and boolean to indicate done or not.
 */
public class Event extends Task {
    public String start;
    /** Formatted date to be printed to the user only */
    protected String formattedDate;

    public Event(String description, String start) {
        super(description);
        this.start = start;
        this.formattedDate = LocalDate.parse(start).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns the Event description & formatted date to the user.
     *
     * @return Event description and date.
     */
    public String recordString() {
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + start + ")";
    }
}
