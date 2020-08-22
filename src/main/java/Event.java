/**
 * Class representing events.
 */
public class Event extends Task {

    /**
     * Variable to store event time.
     */
    protected String at;

    /**
     * Constructor used to create Event.
     *
     * @param description Event description.
     * @param at Event time.
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
