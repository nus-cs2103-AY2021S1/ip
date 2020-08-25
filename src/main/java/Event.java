import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h>Event Task Type</h>
 * This is a type of tasks that start at a specific time and ends at a specific time
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor of Event class.
     * @param description Name of the task input by user.
     * @param at Time of the task input by user.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Getter for the time of the task.
     * @return LocalDate Date of the time of the Event task.
     */
    public LocalDate getAt() {
        return at;
    }

    /**
     * Overridden toString method to output name, type and status of task.
     * @return String This returns a string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getAt().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
