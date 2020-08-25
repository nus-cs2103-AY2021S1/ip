package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of task
 * contains eventTime of task
 */
public class Event extends Task{

    protected LocalDate eventTime;

    /**
     * constructor of Event
     * @param description description of event
     * @param eventTime time which event will happen
     */
    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * represents event as text
     * @return string representation of event
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}