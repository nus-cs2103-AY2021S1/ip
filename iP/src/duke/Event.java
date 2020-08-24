package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDate eventTime;

    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}