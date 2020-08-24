import java.time.LocalDateTime;

// a task that need to specify exact date and time
public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(Boolean isDone, String description, LocalDateTime at) {
        super(isDone, description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}