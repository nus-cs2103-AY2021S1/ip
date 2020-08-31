package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Tasks.Event is a type of Tasks.Task with time.
 */
public class Event extends Task {
    private LocalDate time;

    public Event(String name, String time) {
        super(name, Type.EVENT);
        try {
            this.time = LocalDate.parse(time, DATE_FORMAT_IN);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DATE_FORMAT_OUT) + ")";
    }
}
