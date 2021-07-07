package duke.task;

/**
 * Event class represent tasks that are events.
 */
public class Event extends Task {

    protected String at;

    /**
     * The constructor for an Event task
     * @param description the specific description for the task
     * @param at the specific location of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * @return a string indicating the details of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
