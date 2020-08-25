import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    public String start;
    protected String formattedDate;

    public Event(String description, String start) {
        super(description);
        this.start = start;
        this.formattedDate = LocalDate.parse(start).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String recordString() {
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + start + ")";
    }
}
