import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event class that represents a Task that occurs on a day.
 * It has a Task Description, as well as a Event date.
 */
public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Returns the representative text of the Event.
     * @return Representative text.
     */
    @Override
    public String taskSaver() {
        String type = "E";
        return type + "/" + super.taskSaver() + "/" + at.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Outputs the Event as a String.
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
