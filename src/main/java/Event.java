/**
 * Represents an event.
 */
public class Event extends Task {
    private String date;

    /**
     * Constructs a new Event instance containing a description and date.
     *
     * @param description The event description.
     * @param date The event date.
     */
    Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a string representation of the event containing its description and date.
     *
     * @return The formatted string.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + date  + ")";
    }
}
