import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task object with an added at variable to indicate when task was completed.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate atDate;

    /**
     * Creates a new Event object with the given description and a String at representing when it is occurring.
     * @param description Description of the event needs to be performed
     * @param at String representation of when the event is occurring
     */
    public Event(String description, Priority priority,  String at) {
        super(description, priority);
        this.at = at;
    }

    /**
     * Creates a new Event object with the given description, a String at representing when it is occurring and
     * whether it has been completed.
     * @param description Description of the event needs to be performed
     * @param at String representation of when the event is occurring
     * @param isDone Completion status of the task
     */
    public Event(String description, Priority priority, String at, boolean isDone) {
        super(description, priority, isDone);
        this.at = at;
    }

    /**
     * Creates a new Event object with the given description and a LocalDate at representing when it is occurring.
     * @param description Description of the event needs to be performed
     * @param atDate LocalDate representation of when the event is occurring
     */
    public Event(String description, Priority priority, LocalDate atDate) {
        super(description, priority);
        this.atDate = atDate;
    }

    @Override
    public String toString() {
        String output = "[E]" + super.toString() + " (at: ";
        if (atDate == null) {
            output += at;
        } else {
            output += atDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return output + ")";
    }

    @Override
    public String getSaveFormat() {
        return "E | " + super.getSaveFormat() + " | " + at;
    }
}
