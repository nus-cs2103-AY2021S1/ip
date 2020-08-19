/**
 * Event task that inherits from Task class, and has an additional condition, which is when it is held at.
 */
public class Event extends Task {
    protected String at;

    /**
     * Creates a new Event object
     *
     * @param description details about the Event
     * @param at time/date the event is held at
     * @return Event with a corresponding description and sets it as uncompleted.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Overrides toString method of Task class
     *
     * @return Custom description of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
