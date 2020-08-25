import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;


    public Event(String description, LocalDate date) {
        super(description, "E");
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" (at: %s)", date.format(
                    DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
