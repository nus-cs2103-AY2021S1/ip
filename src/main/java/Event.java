import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents an event. An event has both a description and a date/time indicating
 * when the event occurs.
 */
public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns a String representation of the Event that will be saved in
     * the hard disk.
     * @return a String representation of the Event.
     */
    public String formattedString() {
        return "E | " + (super.isDone? 1 : 0) + " | " + super.description + " | " +
                at;
    }
}
