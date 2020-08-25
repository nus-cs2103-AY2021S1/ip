import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate timeDescription;
    private String timeDescriptionFormatted;

    public Event(String description, LocalDate timeDescription) {
        super(description, "E");
        this.timeDescription = timeDescription;
        this.timeDescriptionFormatted = this.timeDescription.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Event(String description, String timeDescriptionFormatted, boolean isDone) {
        super(description, "E", isDone);
        this.timeDescriptionFormatted = timeDescriptionFormatted;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description
                + "(at: " + this.timeDescriptionFormatted + ")";
    }
}
