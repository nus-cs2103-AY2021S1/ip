import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDate at, String time) {
        super(description);
        this.at = at;
        this.time = time;
    }

    public String display() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + (time == null || time.isEmpty() ? "" : time)+ ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at  + " "
                + (time == null || time.isEmpty() ? "" : time) + ")";
    }
}