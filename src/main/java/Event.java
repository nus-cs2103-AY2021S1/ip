/**
 * Represents an event. An event has both a description of the activity,
 * and a date/time indicating when it occurs.
 */
public class Event extends Task {

    private String at;

    /**
     * Returns an Event.
     * @param description Description of the Event.
     * @param at Date/time at which the Event occurs.
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
     * Returns a String representation of the Event that will be saved
     * in the hard disk.
     * @return String representation of the Event.
     */
    public String getFormattedString() {
        return "E | " + (super.isDone ? 1 : 0) + " | " + super.description + " | "
                + at;
    }
}
