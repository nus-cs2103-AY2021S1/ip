package task;

public class Event extends Task {
    protected String at;

    /**
     * Creates Event object.
     * @param description String description to describe Event.
     * @param at String to describe where/when Event occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates Event object.
     * @param description String description to describe Event.
     * @param at String to describe where/when Event occurs.
     * @param isDone Boolean to indicate completion status of Event.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Creates Event object.
     * @param description String description to describe Event.
     * @param at String to describe where/when Event occurs.
     * @param isDone Boolean to indicate completion status of Event.
     * @param tag String to tag Event.
     */
    public Event(String description, String at, boolean isDone, String tag) {
        super(description, isDone, tag);
        this.at = at;
    }

    /**
     * Returns description of this task and its completion status, with its event date/location.
     * @return String that describes task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
