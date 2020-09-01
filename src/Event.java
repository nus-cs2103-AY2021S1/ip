import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    public Event(String description, LocalDate date) {
        super(description, date);
    }

    public Event(String description, LocalDate date, String duration) {
        super(description, date, duration);
    }

    @Override
    public String toString() {
        String print = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return duration == null
                ? "[D]" + super.toString() + " (at: " + print + ")"
                : "[D]" + super.toString() + " (at: " + print + " " + duration.toString() + ")";
    }
}