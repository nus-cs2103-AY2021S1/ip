package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an Event Task.
 */
public class Event extends Task {

    protected LocalDateTime localDateTime;

    /**
     * Instantiates Event.
     *
     * @param description   The description of event.
     * @param localDateTime The local date and time for said event.
     */
    public Event(String description, LocalDateTime localDateTime) {
        super(description);
        this.localDateTime = localDateTime;
    }

    /**
     * Instantiates Event.
     *
     * @param description   The description of event.
     * @param isDone        Status of completion of event.
     * @param localDateTime The local date and time for said event.
     */
    public Event(String description, boolean isDone, LocalDateTime localDateTime) {
        super(description, isDone);
        this.localDateTime = localDateTime;
    }

    /**
     * Formats the date and time.
     *
     * @return String containing the formatted date and time.
     */
    String formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyy, hh:mm a");
        return this.localDateTime.format(formatter);
    }

    /**
     * Overrides toString() method.
     *
     * @return String for Event Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatDateTime() + ")";
    }
}
