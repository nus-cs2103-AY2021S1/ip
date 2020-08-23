import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent an event object
 */

public class Event extends Task {

    private LocalDate at;

    /**
     * Constructor for event class
     * @param description description of the event
     * @param at duedate of the event
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the duedate of the event
     * @return
     */
    protected LocalDate getAt() {
        return this.at;
    }

    /**
     * Prints the event object
     * @return string representation of the event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("d MMM uuuu")) + ")";
    }
}