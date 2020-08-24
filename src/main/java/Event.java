import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected LocalDateTime dateAndTime;

    public Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = LocalDateTime.parse(dateAndTime, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + dateAndTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}
