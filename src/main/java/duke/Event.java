package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    /** Event of task in local date time */
    protected LocalDateTime dateAndTime;

    /**
     * Constructs a new instance of an Event with description and date.
     *
     * @param description Description of event.
     * @param dateAndTime Date and time of event.
     */
    public Event(String description, String dateAndTime) {
        super(description, Type.EVENT);
        this.dateAndTime = LocalDateTime.parse(
                dateAndTime, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"));
    }

    /**
     * Constructs a new instance of an Event with description, date and completion status.
     *
     * @param description Description of event.
     * @param dateAndTime Date and time of event.
     * @param isDone Completion status.
     */
    public Event(String description, String dateAndTime, boolean isDone) {
        super(description, Type.EVENT, isDone);
        this.dateAndTime = LocalDateTime.parse(
                dateAndTime, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"));
    }

    /**
     * Returns date and time of event.
     *
     * @return Date and time of event as a String.
     */
    @Override
    public String getTime() {
        return this.dateAndTime.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"));
    }

    /**
     * Returns string representation of an event.
     * @return String representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + dateAndTime.format(DateTimeFormatter
                                .ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}
