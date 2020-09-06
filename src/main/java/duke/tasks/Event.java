package duke.tasks;

/**
 * Event is a type of Task
 */
public class Event extends Task {

    private String timePeriod;

    /**
     * Constructs an event
     *
     * @param name       Description of the event.
     * @param timePeriod The period of the event.
     */
    public Event(String name, String timePeriod) {
        super(name);
        this.timePeriod = timePeriod;
    }

    /**
     * Gets the type of Event.
     * @return "E" which represents event
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Gets the period of time of the event.
     * @return The period of time of the event
     */
    @Override
    public String getTime() {
        return timePeriod;
    }

    /**
     * The toString method of Event.
     *
     * @return The required String format of an event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timePeriod + ")";
    }

}
