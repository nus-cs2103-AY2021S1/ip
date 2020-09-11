import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// a task that need to specify exact date and time
public class Event extends Task {
    protected LocalDateTime eventDateTime;

    /**
     * Event constructor taking description and event date and time.
     * @param description
     * @param eventDateTime
     */
    public Event(String description, LocalDateTime eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
    }

    /**
     * Event constructor taking boolean isdone, description and event date and time.
     * @param isDone
     * @param description
     * @param eventDateTime
     */
    public Event(Boolean isDone, String description, LocalDateTime eventDateTime) {
        super(isDone, description);
        this.eventDateTime = eventDateTime;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    @Override
    public LocalDateTime getDueDateTime() {
        return eventDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma")) + ")";
    }
}
