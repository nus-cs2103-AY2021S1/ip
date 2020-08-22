import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime eventDateTime;

    public Event(String description, String at) {
        super(description);
        this.eventDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")) + ")";
    }
}
