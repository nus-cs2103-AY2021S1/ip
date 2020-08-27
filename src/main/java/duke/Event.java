package duke;

/**
 * Creates a type of task called event which takes place at certain period of time.
 */
public class Event extends Task {
    protected String time;

    public Event(String taskname, boolean status, String time) {
        super(taskname, status);
        this.time = time;
    }

    /**
     * Returns the string representation of a event
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
