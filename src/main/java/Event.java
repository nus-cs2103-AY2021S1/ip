/**
 * Represents a event task.
 */
public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[E]" + super.toString();
    }
}
