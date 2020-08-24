import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event task.
 */
public class Event extends Task{
    public LocalDate time;
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public Event(boolean done, String description, LocalDate due) {
        super(done, description,'E');
        this.time = due;
        String unparseMessage = "E";
        if (done) {
            unparseMessage += "1";
        } else {
            unparseMessage += "0";
        }
        unparseMessage += description;
        unparseMessage += ",";
        unparseMessage += due;
        super.unparseMessage = unparseMessage;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
