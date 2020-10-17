package duke.task;

/**
 * Represents a Event task.
 */
public class Event extends Task {
    private String eventTime;

    /**
     * Creates an {@code Event} with given content and event time.
     *
     * @param taskContent A String of content.
     * @param eventTime A String of event time.
     */
    public Event(String taskContent, String eventTime) {
        super(taskContent);
        this.eventTime = eventTime;
    }

    @Override
    public String getType() {
        return "E";
    }
    @Override
    public String getDate() {
        return this.eventTime;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTime + ")";
    }
}
