import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate eventTime;

    public Event(String description, LocalDate eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + this.eventTime.format(formatter) + ")";
    }
}
