/**
 * Encapsulates the idea of events, tasks that start
 * and end at specific times. Thus it inherits from Task
 */

public class Event extends Task {

    // constants
    private final String LABEL = "E";

    // instance variables
    private String time;

    // constructor
    Event(boolean isDone, String taskName, String time) {
        super(isDone, taskName);
        this.time = time;
    }

    /**
     * Returns string representation of an event
     * @return string representation of an event object
     */
    @Override
    public String toString() {
        return "[" + LABEL + "]"
                + super.toString() + " (at: " + time + ")";
    }
}