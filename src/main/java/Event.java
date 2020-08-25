import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates Event object.
     * @param description Event description.
     * @param at Event timing.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates Event object.
     * @param done Done state of event.
     * @param description Event description.
     * @param at Event timing.
     */
    public Event(String done, String description, LocalDate at) {
        super(description);
        this.at = at;
        this.isDone = done.equals("1");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " 
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    
    @Override
    public String saveString() {
        return "E" + super.saveString() + " , " + this.at;
    }
}
