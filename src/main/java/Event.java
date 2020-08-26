import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * represents an event task
 */

public class Event extends Task {
    protected String eventTime;

    public Event(String event) throws EmptyDescriptionException {
        super(event.substring(6, event.indexOf("/")-1));
        if (description.length() <= 6) {
            throw new EmptyDescriptionException("oops! the description of an event cannot be empty");
        }
        this.eventTime = event.substring(event.indexOf("/")+4);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        String eventTime;
        try {
            LocalDate localDate = LocalDate.parse(this.eventTime);
            eventTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            eventTime = this.eventTime;
        }

        sb.append("[E]")
                .append(super.toString())
                .append(" (").append("at: ").append(eventTime).append(")");
        return sb.toString();
    }

    public String getEventTime() {
        String eventTime;
        try {
            LocalDate localDate = LocalDate.parse(this.eventTime);
            eventTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            eventTime = this.eventTime;
        }
        return eventTime;
    }
}
