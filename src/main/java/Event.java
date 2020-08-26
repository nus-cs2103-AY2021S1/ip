import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event by its taskName, date of event and whether or not it has been completed.
 *
 * @author amelia
 * @version 1.0
 * @since 2020-08-26
 */
public class Event extends Task {
    private LocalDateTime date;

    Event(String taskName, boolean isCompleted, LocalDateTime date) {
        super(taskName, isCompleted);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mm a")) + ")";
    }
}
