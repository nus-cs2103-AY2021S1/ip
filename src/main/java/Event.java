import java.time.LocalDate;

/**
 * Represents a task occurring at a certain time
 */
public class Event extends Task {

    String by;

    /**
     * @param description Task description
     * @param date Date of the event
     */
    public Event(String description, LocalDate date) {
        super(description, date);
    }

    /**
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.toString() + ")\n";
    }
}