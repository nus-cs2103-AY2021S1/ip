/**
 * Represents an event. An event is a task that has a description of the activity,
 * and a date or time indicating when it occurs.
 */
public class Event extends Task {

    private String at;

    /**
     * Constructs an Event with the specified description and date.
     * @param description Description of the Event.
     * @param at Date at which the Event occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns a representation of the Event that will be saved in the hard disk.
     * @return String representation of the Event.
     */
    public String getFormattedString() {
        return "E | " + (super.isDone ? 1 : 0) + " | " + super.description + " | "
                + at;
    }
}
