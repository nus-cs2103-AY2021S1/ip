/**
 * Encapsulates an Event.
 * Event are tasks that we have to attend at a given time slot.
 */
public class Event extends Task {

    /** Symbol representing the type of Task this is */
    protected static final String SYMBOL = "E";
    /** String separator used to separate the task description and the timing*/
    public static final String SPLITTER = " /at ";

    /** Timing of the event represented by a String */
    protected String timing;

    /**
     * Creates an Event object with the given event description and timing.
     * @param taskDescription full description of the event including task and timing
     */
    public Event(String taskDescription) {
        super(taskDescription.split(SPLITTER)[0]);
        timing = taskDescription.split(SPLITTER)[1];
    }

    /**
     * Creates a new Event object by manually setting the description and timing
     * @param eventDescription description of the event task only
     * @param timing string description of the timing of the event
     */
    private Event(String eventDescription, String timing) {
        super(eventDescription);
        this.timing = timing;
    }

    /**
     * Returns the String representation of the object.
     * Updated from the superclass Task to include the type of Task this object is and include the timing.
     * @return returns a String representing an Event with a "[E]" identifier and timing
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", SYMBOL, super.toString(), timing);
    }

    /**
     * Returns a summary of the Event.
     * @return string containing type, completion status, description and timing
     */
    @Override
    public String getSummary() {
        return String.format("%s|%d|%s|%s",
                SYMBOL,
                (isCompleted() ? 1 : 0),
                description,
                timing);
    }

    /**
     * Returns an Event object corresponding to the summary given.
     * @param summary string summary of the Event object to be reconstructed
     * @return Event object representing the summary given
     */
    public static Event reconstructFromSummary(String summary) {
        String[] details = summary.split("\\|");
        if (details.length != 4) {
            throw new DukeException("Wrong number of details!");
        } else if (!(details[1].equals("1") || details[1].equals("0"))) {
            throw new DukeException("Invalid completion status! Ensure that it is either 0 or 1");
        }
        return new Event(details[2], details[3]);
    }


}
