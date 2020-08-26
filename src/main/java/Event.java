import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toSave() {
        return "E | " +  super.toSave() + " | " + at;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
