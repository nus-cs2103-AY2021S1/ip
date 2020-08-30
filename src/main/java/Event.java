import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends TimedTask {

    public Event(String description, String by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.formatBy() + ")";
    }
}
