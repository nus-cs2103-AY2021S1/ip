package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Event class encapsulates information and methods pertaining to an Event.
 */
public class Event extends Task implements Comparable<Event> {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Creates a Event with the given details and date.
     * The Event is set as not done.
     *
     * @param details Details of event.
     * @param startDate Starting date and time of the event.
     * @param endDate Ending date and time of the event.
     */
    public Event(String details, LocalDateTime startDate, LocalDateTime endDate) {
        super(details);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates a Event with the given details and date.
     * The Event is set as done if isDone is true.
     *
     * @param details Details of the deadline.
     * @param isDone True if the deadline is done.
     * @param startDate Starting date and time of the event.
     * @param endDate Ending date and time of the event.
     */
    public Event(String details, boolean isDone, LocalDateTime startDate, LocalDateTime endDate) {
        super(details, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the Event to be saved in hard disk.
     *
     * @return String representation of the Event.
     */
    @Override
    public String store() {
        return "E " + super.store() + " /at " + startDate + " to " + endDate;
    }

    /**
     * Returns a string representation of the Event to be printed.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a", Locale.ENGLISH))
                + " to "
                + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a", Locale.ENGLISH))
                + ")";
    }

    @Override
    public int compareTo(Event event) {
        return this.startDate.compareTo(event.startDate);
    }
}
