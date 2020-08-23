import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event is a subtype of Task which represents a task
 * which requires an attendance at a given time.
 */

public class Event extends Task {

    /** The date of the event. */
    protected LocalDate atDate;

    /** The time of the event. */
    protected LocalTime atTime;

    /**
     * Constructor for Event.
     * @param description the description of the task.
     * @param at the time of the task.
     */
    public Event(String description, String at) throws DukeException {
        super("E", description);
        try {
            String[] dateTime = at.split(" ");
            String dateValue = dateTime[0];
            if (dateValue.equals("today")) {
                dateValue = LocalDate.now().toString();
            } else if (dateValue.equals("tomorrow")) {
                dateValue = LocalDate.now().plusDays(1).toString();
            }
            this.atDate = LocalDate.parse(dateValue);
            this.atTime = LocalTime.parse(dateTime[1]);
        } catch (DateTimeParseException de) {
            throw new DukeException("Invalid date time format!");
        }
    }

    public Event(String description, String at, boolean isDone) throws DukeException {
        super("E", description, isDone);
        try {
            String[] dateTime = at.split(" ");
            String dateValue = dateTime[0];
            if (dateValue.equals("today")) {
                dateValue = LocalDate.now().toString();
            } else if (dateValue.equals("tomorrow")) {
                dateValue = LocalDate.now().plusDays(1).toString();
            }
            this.atDate = LocalDate.parse(dateValue);
            this.atTime = LocalTime.parse(dateTime[1]);
        } catch (DateTimeParseException de) {
            throw new DukeException("Invalid date time format!");
        }
    }

    @Override
    public String getDescription() {
        return description + " / " + atDate + " " + atTime;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s, %s)",
                atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                atTime.format(DateTimeFormatter.ofPattern("hh:mm a")));
    }
}
