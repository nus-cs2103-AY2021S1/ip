/**
 * Represents a event with inherited functionalities from Task.
 * @author Lim Zi Yang
 */
public class Event extends Task {
    private final String time;

    /**
     * Creates an undone event.
     * @param description Description of event.
     * @param time Time of event.
     */
    Event (String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Creates an event.
     * @param description Description of event.
     * @param time Time of event.
     * @param isDone Whether the event is done.
     */
    private Event (String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Marks event as done.
     * @return A done event.
     */
    @Override
    public Event markDone() {
        return new Event(getDescription(), this.time, true);
    }

    /**
     * Overridden toString method.
     * @return String value of event.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + "(at: " + time + ")";
    }
}
