/**
 * Represents a event task.
 */
public class Event extends Task {

    /**
     * Constructs a event object.
     *
     * @param description event description
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Constructs a event object.
     *
     * @param description event description
     */
    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[E]" + super.toString();
    }
}
