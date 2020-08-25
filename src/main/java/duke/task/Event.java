package duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Event class encapsulates information and methods pertaining to an
 * Event.
 *
 * @author  Yen Pin Hsuan
 * @version 1.0
 */
public class Event extends Task {
    private LocalDateTime date;

    /**
     * Create a Event with the given details and date.
     * The Event is set as not done.
     * @param details Details of deadline.
     * @param date Date of the deadline.
     */
    public Event(String details, LocalDateTime date) {
        super(details);
        this.date = date;
    }

    /**
     * Create a Event with the given details and date.
     * The Event is set as done if isDone is true.
     * @param details Details of the deadline.
     * @param isDone True if the deadline is done.
     * @param date Date of the deadline.
     */
    public Event(String details, boolean isDone, LocalDateTime date) {
        super(details, isDone);
        this.date = date;
    }

    /**
     * Return a string representation of the Event to be saved in hard disk.
     * @return String representation of the Event.
     */
    @Override
    public String store() {
        String done = this.isDone ? "T " : "F ";
        return "E " + done + this.details + " /at " + this.date + "\n";
    }

    /**
     * Return a string representation of the Event to be printed.
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" +super.toString() + " (at: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a", Locale.ENGLISH)) + ")";
    }
}
