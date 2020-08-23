/**
 * Encapsulates an Event.
 * Events are tasks that we have to attend at a given time slot.
 */
public class Events extends Task {

    /**
     * timing of the event represented by a String
     */
    protected String timing;

    /**
     * String separator used to separate the task description and the timing
     */
    public static final String SPLITTER = " /at ";

    /**
     * Creates an Event object with the given event description and timing.
     * @param taskDescription full description of the event including task and timing
     */
    public Events(String taskDescription) {
        super(taskDescription.split(SPLITTER)[0]);
        timing = taskDescription.split(SPLITTER)[1];
    }

    /**
     * Returns the String representation of the object.
     * Updated from the superclass Task to include the type of Task this object is and include the timing.
     * @return returns a String representing an Event with a "[E]" identifier and timing
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), timing);
    }



}
