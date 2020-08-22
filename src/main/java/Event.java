import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime at;
    private static final DateTimeFormatter FormatDateTime = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String formatStyling() {
        return String.format("event,%s%s", at, super.formatStyling());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(FormatDateTime) + ")";
    }
}
