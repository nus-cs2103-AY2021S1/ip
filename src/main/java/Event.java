import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime date;

    public Event(String details, LocalDateTime date) {
        super(details);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" +super.toString() + " (at: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a", Locale.ENGLISH)) + ")";
    }
}
