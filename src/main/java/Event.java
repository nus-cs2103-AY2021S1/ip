import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDateTime localTime;

    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        this.at = at;
        this.localTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String writeSaveFormat() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + localTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }
}
