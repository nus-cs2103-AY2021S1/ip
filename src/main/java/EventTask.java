import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private final LocalDateTime eventDate;

    public EventTask(String name, LocalDateTime date, String eventDate) {
        super(name, date);
        this.eventDate = LocalDateTime.parse(eventDate, formatter);
    }

    public String getEventDate() {
        return eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.getEventDate() + ")";
    }
}
