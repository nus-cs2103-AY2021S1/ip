import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String eventDateString;
    private LocalDate eventDate = null;

    public Event(String taskName, String eventTime) {
        super(taskName);
        try {
            eventDate = LocalDate.parse(eventTime);
            this.eventDateString = eventDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } catch (DateTimeException e) {
            this.eventDateString = eventTime;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDateString + ")";
    }
}
