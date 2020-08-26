import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate eventTime;

    public Event(String event) throws EmptyDescriptionException {
        super(event.substring(6, event.indexOf("/")-1));
        if (description.length() <= 6) {
            throw new EmptyDescriptionException("oops! the description of an event cannot be empty");
        }
        this.eventTime = LocalDate.parse(event.substring(event.indexOf("/")+4));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]")
                .append(super.toString())
                .append(" (").append("at: ")
                .append(this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                .append(")");
        return sb.toString();
    }
}
