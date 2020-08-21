import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    public LocalDate time;
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
