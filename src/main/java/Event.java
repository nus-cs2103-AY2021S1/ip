import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDateTime localDateTime;

    public Event(String description, LocalDateTime localDateTime) {
        super(description);
        this.localDateTime = localDateTime;
    }

    private String formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyy, hh:mm a");
        return this.localDateTime.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + formatDateTime() + ")";
    }
}
