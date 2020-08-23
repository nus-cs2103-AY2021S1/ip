import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    LocalDate eventTime;

    public Event(String description, boolean isDone) {
        super(description.split(" /at ")[0], false);
        this.eventTime = LocalDate.parse(description.split(" /at ")[1]);
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + this.eventTime.format(formatter) + ")";
    }
}
