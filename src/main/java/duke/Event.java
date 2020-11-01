package duke;

/**
 * Event is a subclass of Task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private String at;

    /**
     * Constructor of Event object.
     *
     * @param description Takes in the description of the Event object.
     * @param at Takes in the date in which the task is on in YYYY-MM-DD format.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the task type of Event.
     *
     * @return the task type of Event.
     */
    @Override
    public String getTaskType() {
        return "Event";
    }

    /**
     * Formats the string of a Event object.
     *
     * @return a formatted string for a Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Formats the string of a Event object to be stored into hard drive.
     *
     * @return a formatted string suitable for storage in hard drive for a Event object.
     */
    @Override
    public String toStringInFile() {
        return "E" + super.toStringInFile() + " | " + this.at;
    }

}
