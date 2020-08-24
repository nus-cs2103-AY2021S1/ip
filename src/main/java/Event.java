import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime by;

    public Event(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Event(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return Time.convertTimeToSave(by);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Time.toString(by) + ")";
    }
}
