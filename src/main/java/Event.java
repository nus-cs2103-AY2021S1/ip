import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;

    public Event(String name, LocalDate date) {
        super(name, "[E]");
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
