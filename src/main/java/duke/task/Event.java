package duke.task;

/**
 * Represents a task with a description and date of event.
 */
public class Event extends Task {
    private String eventTime;

    /**
     * Constructs a task with the specified description and date of event.
     *
     * @param description Decription of event.
     * @param eventTime Date of event.
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Returns a String representation of an event task.
     *
     * @return String representation of an event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTime + ")";
    }
}
