import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// a task that need to specify exact date and time
public class Event extends Task {

    protected LocalDateTime eventDateTime;

    public Event(String description, LocalDateTime eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
    }

    public Event(Boolean isDone, String description, LocalDateTime eventDateTime) {
        super(isDone, description);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma")) + ")";
    }
}