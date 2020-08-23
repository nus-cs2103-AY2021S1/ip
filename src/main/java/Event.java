import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    private String dateFormat() {
        return at.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateFormat() + ")";
    }

    public String writeToFile() {
        return String.format("E | %b | %s | %s", this.isDone, this.description, this.at);
    }
}
