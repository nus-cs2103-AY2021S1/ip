import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    protected String atString;
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.atString = at.format(DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm"));
        this.at = at;
    }

    public LocalDateTime getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atString + ")";
    }
}
