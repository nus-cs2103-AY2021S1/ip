package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of task.
 * Contains eventTime of task.
 */
public class Event extends Task {

    protected LocalDate eventTime;

    /**
     * Constructor of Event.
     *
     * @param description Description of event.
     * @param eventTime Time which event will happen.
     */
    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Returns string representation of event.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                eventTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
