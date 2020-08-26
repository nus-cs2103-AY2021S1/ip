import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime on;
    private static final DateTimeFormatter E_DATETIME_FORMAT = DateTimeFormatter.ofPattern("EEEE, MMM dd uuuu, ha");

    public Event(String description, LocalDateTime on) {
        super(description);
        this.on = on;
    }

    public String getEventDateTime() {
        return on.format(E_DATETIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + getEventDateTime() + ")";
    }
}
