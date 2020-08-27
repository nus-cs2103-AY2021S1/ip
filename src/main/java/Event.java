import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class representing events.
 */
public class Event extends Task {

    /**
     * Variable to store event time.
     */
    protected LocalDate at;

    /**
     * Constructor used to create Event.
     *
     * @param description Event description.
     * @param at Event time.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor used to create Event.
     *
     * @param description Event description.
     * @param at Event time.
     * @param isDone Describes if task is completed.
     */
    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns simple string format for file.
     *
     * @return Simple string description.
     */
    @Override
    public String toStringSimple() {
        return "E | " + super.toStringSimple() + " | " + at;
    }

    @Override
    public String toString() {
        String formattedDate = this.at.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }
}
