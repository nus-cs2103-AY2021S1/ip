package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Event class encapsulates information and methods pertaining to an
 * Event.
 */
public class Event extends Task implements Comparable<Event> {
    private LocalDateTime date;

    /**
     * Creates a Event with the given details and date.
     * The Event is set as not done.
     *
     * @param details Details of deadline.
     * @param date Date of the deadline.
     */
    public Event(String details, LocalDateTime date) {
        super(details);
        this.date = date;
    }

    /**
     * Creates a Event with the given details and date.
     * The Event is set as done if isDone is true.
     *
     * @param details Details of the deadline.
     * @param isDone True if the deadline is done.
     * @param date Date of the deadline.
     */
    public Event(String details, boolean isDone, LocalDateTime date) {
        super(details, isDone);
        this.date = date;
    }

    /**
     * Returns a string representation of the Event to be saved in hard disk.
     *
     * @return String representation of the Event.
     */
    @Override
    public String store() {
        return "E " + super.store() + " /at " + this.date;
    }

    /**
     * Returns a string representation of the Event to be printed.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a", Locale.ENGLISH)) + ")";
    }

    @Override
    public int compareTo(Event event) {
        return this.date.compareTo(event.date);
    }
}
