import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Event is a type of Task with time.
 */
class Event extends Task {
    private LocalDate time;

    Event(String name, String time) {
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
