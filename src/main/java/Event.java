import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate eventTime;

    public Event(String event) {
        super(event.substring(6, event.indexOf("/")-1));
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
