import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends DatedTask {
    public Event(String name, LocalDate date) {
        super(name, date);
    }

    public Event(String name, boolean completed, LocalDate date) {
        super(name, completed, date);
    }

    @Override
    public String format() {
        return "E" + SAVE_DELIMITER + super.format();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
