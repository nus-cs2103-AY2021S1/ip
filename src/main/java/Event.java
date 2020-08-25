import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate timeDescription;

    public Event(String description, LocalDate timeDescription) {
        super(description, "E");
        this.timeDescription = timeDescription;
    }

    public Event(String description, String timeDescription, boolean isDone) {
        super(description, "E", isDone);
        this.timeDescription = timeDescription;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description
                + "(at: " + this.timeDescription.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
