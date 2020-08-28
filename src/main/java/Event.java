import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String encode() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("D | %d | %s | %s", getStatusIcon(), description , dateFormatter.format(at));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}