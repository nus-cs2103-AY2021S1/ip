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
        super("E", description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super("E", description, isDone);
        this.at = at;
    }

    @Override
    public String getDescription() {
        return description + " / " + at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
