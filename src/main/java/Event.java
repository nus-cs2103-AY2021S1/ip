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
    public String toString() {
        String formattedDate = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + "(at: " + formattedDate + ")";
    }

    public String toDataString() {
        return String.format("D|%s|%s|%s", super.isDone, super.description, this.at);
    }
}
