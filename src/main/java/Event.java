import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String STRING_FORMAT = "[E][%s] %s (at: %s)";

    protected LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format(Event.STRING_FORMAT, getStatusIcon(), description, 
                eventTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")));
    }
}
