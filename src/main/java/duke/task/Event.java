package main.java.duke.task;

/**
 * Represents a task with an event.
 */
public class Event extends Task {

    protected String at;

    /**
     * Creates a new instance of an Event.
     *
     * @param description Description of the task with deadline.
     * @param at Event's date and time.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets date and time of an event task.
     *
     * @return Event's date and time.
     */
    public String getAt() {
        return at;
    }

    /**
     * Converts an Event object to a string.
     *
     * @return A string displaying the task and its status.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
