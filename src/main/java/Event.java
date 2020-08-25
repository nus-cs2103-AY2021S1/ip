import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate time;

    public Event(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
