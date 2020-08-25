import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Event extends Task implements Serializable {

   protected LocalDateTime eventDateTime;
   protected String eventName;

    public Event(String eventName, LocalDateTime eventDateTime) {
        super(eventName);
        this.eventDateTime = eventDateTime;
    }

    public String getEventDateTime() throws DateTimeParseException {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" dd-MMM-yyyy hh:mm a", Locale.ENGLISH);
        return eventDateTime.format(outputFormat);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (at:" + getEventDateTime() + ")";
    }
}