import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    public Event(String description, LocalDateTime date, boolean isCompleted) {
        super(description, isCompleted);
        this.date = date;
    }

    @Override
    public Event markCompleted() {
        return new Event(description, date, true);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), date);
    }
}
