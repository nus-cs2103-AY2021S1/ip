package duke.task;

/**
 * Represents a task with a description and date of event.
 */
public class Event extends Task {
    protected String time;

    /**
     * Creates a task with the specified description and date of event.
     *
     * @param description Decription of event.
     * @param time Date of event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a String representation of an event task.
     *
     * @return String representation of an event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}
