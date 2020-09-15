package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event with a specified description, date, and boolean to indicate done or not.
 */
public class Event extends Task {
    protected String start;
    /** Formatted date to be printed to the user only */
    protected String formattedDate;

    /**
     * Constructor for the Event class
     */
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
    public String formattedDateString() {
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

    /**
     * Returns the Event description, formatted date and tags to the user.
     *
     * @return Event description, data and tags
     */
    public String formattedDateStringWithTags() {
        return "[E]" + super.toString() + " (at: " + formattedDate + ")"
                + (this.tags.size() == 0 ? "" : "\ntags: " + super.getTags());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + start + ")"
                + (this.tags.size() == 0 ? "" : "\ntags: " + super.getTags());
    }
}
