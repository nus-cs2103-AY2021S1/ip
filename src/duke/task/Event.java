package duke.task;

/**
 * Event class that represents an event task for the user
 */
public class Event extends Task {
    protected String at;

    /**
     * Event Class constructor. Create a new Event with task description and time of the event.
     *
     * @param description give the description of the event
     * @param at    the time of the event
     *
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * To String method of event
     * @return  a String that describes the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
