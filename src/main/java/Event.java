import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles event-based Tasks.
 */

public class Event extends DatedTask {
    /**
     * Constructor for Event-based Tasks.
     * @param name Description of Task.
     * @param date Date of Task.
     */
    public Event(String name, LocalDate date) {
        super(name, date);
    }

    /**
     * Constructor for Event-based Tasks.
     * @param name Description of Task.
     * @param completed State of completion of Task.
     * @param date Date of Task.
     */
    public Event(String name, boolean completed, LocalDate date) {
        super(name, completed, date);
    }

    /**
     * Represents Event in format to be saved.
     * @return Saved representation of Event object.
     */
    @Override
    public String format() {
        return "E" + SAVE_DELIMITER + super.format();
    }

    /**
     * Represents Event in String form.
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
