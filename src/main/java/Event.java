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

    /**
     * Constructor used to create Event.
     *
     * @param description Event description.
     * @param at Event time.
     * @param isDone Describes if task is completed.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns simple string format for file.
     *
     * @return Simple string description.
     */
    @Override
    public String toStringSimple() {
        return "E | " + super.toStringSimple() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
