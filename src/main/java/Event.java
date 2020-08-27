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

    @Override
    public String toString() {
        String formattedDate = this.at.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }
}
