package duke;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class representing events.
 */
class Event extends Task {

    /**
     * Variable to store event time.
     */
    protected LocalDate eventDate;

    /**
     * Constructor used to create Event.
     *
     * @param description Event description.
     * @param eventDate Event time.
     */
    Event(String description, LocalDate eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Constructor used to create Event.
     *
     * @param description Event description.
     * @param eventDate Event time.
     * @param isDone Describes if task is completed.
     */
    Event(String description, LocalDate eventDate, boolean isDone) {
        super(description, isDone);
        this.eventDate = eventDate;
    }

    /**
     * Returns simple string format for file.
     *
     * @return Simple string description.
     */
    @Override
    String toStringForStorage() {
        return "E | " + super.toStringForStorage() + " | " + eventDate;
    }

    @Override
    public String toString() {
        String formattedDate = this.eventDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }
}
