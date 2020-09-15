import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private final String description;
    private final LocalDate date;
    private final LocalTime time;

    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {

        return "[E]" + "[" + getStatusIcon() + "]" + "[" + this.getPriority() + "]" + " " + this.description
                + "(at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", " + this.time + ")";
    }
}