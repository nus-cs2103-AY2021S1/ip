import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime timeOfEvent;
    protected static DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    

    public Event(String description, String timeOfEvent) {
        super(description);
        this.timeOfEvent = LocalDateTime.parse(timeOfEvent, dateTimeInputFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeOfEvent.format(dateTimeOutputFormatter) + ")";
    }
}
