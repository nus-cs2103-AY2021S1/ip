import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event(String description, String at) {
        super(description);
        this.date = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
