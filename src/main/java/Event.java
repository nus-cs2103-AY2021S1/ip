import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime datetime;

    public Event(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
