/**
 * Event is a subtype of Task which represents a task
 * which requires an attendance at a given time.
 */

public class Event extends Task {

    /** The time of the event. */
    protected String at;

    /**
     * Constructor for Event.
     * @param description the description of the task.
     * @param at the time of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
