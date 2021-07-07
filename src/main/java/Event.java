/**
 * Represents an event task.
 */
public class Event extends Task {
    protected static final String TYPE = "event";

    protected String at;

    Event(String description, String at) {
        super(description, TYPE);
        this.at = at;
    }

    @Override
    public String getTiming() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
