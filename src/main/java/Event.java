import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task that has a description, a date, and a state of whether it has been done.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Creates a new event task.
     * @param description the description of the event task
     * @param at the date when the task should take place
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        assert at != null;
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSave() {
        assert at != null;
        return "E " + super.toSave() + "/" + at;
    }
}
