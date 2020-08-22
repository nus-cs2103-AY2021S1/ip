import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public Event(String name, LocalDate date) {
        super(name, "[E]", date);
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), super.getDate().get().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
