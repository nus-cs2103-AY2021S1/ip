import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>Represents an Event</h1>
 * Contains the details of the event, whether it is completed,
 * and the date of the event.
 */
public class Event extends Task {
    protected String date;
    private LocalDate localDate;

    public Event(String task, String date, boolean isCompleted) {
        super(task, isCompleted);
        this.date = date;
        localDate = LocalDate.parse(date);
    }

    /**
     * An overridden method that returns a String with the Task type,
     * event details, event date formatted in MMM d yyyy format.
     * @return String with all details of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
