package luke.task;

/**
 * Represents an event and its time for the user.
 */
public class Event extends Task {
    protected String at;

    /**
     * Creates a Event object that indicates the task and its time.
     *
     * @param description details about the task
     * @param at time that the event takes place
     */
    public Event(String description, String at) {
        super(TaskType.EVENT, description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toDataString() {
        return String.format("E|%s|%s", super.toDataString(), this.getAt());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
