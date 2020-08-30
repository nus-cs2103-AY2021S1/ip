package tasklist;

/**
 * Event class is a subclass of Task.
 * Event stores each Event's description and event time.
 * @author Maguire Ong
 */

public class Event extends Task {
    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}
