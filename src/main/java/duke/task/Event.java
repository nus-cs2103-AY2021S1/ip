package duke.task;

/**
 * Represents a specific task which is an event.
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description) {
        super(description);
        this.at = null;
    }

    @Override
    public String toString() {
        return at == null ? "[E]" + super.toString() : "[E]" + super.toString() + " (by: " + at + ")" ;
    }
}