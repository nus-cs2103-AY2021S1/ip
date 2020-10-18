import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the Event object, contains information about the event timing. 
 */
public class Event extends Task {

    /**
     * Represents the event timing.
     */
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts to a string format to be saved in a text file.
     *
     * @return a string representation of the event object.
     */
    @Override
    public String saveAsString() {
        return "E" + super.saveAsString() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
