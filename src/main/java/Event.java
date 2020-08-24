public class Event extends Task {

    protected String time;

    /**
     * Creates a Event object that represents an event.
     *
     * @param description Description of the event.
     * @param time The time of the event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Return the Event time.
     *
     * @return The Event time.
     */
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}