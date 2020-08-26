import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends DatedTask {
    public Event(String description, LocalDate at) {
        super(description, at);
    }

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone, at);
    }

    @Override
    public String toSave() {
        return "E | " +  super.toSave();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
