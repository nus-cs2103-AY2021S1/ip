package duke.task;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private String time;

    /**
     * Constructs a event task.
     *
     * @param description a string describing
     *                    the task.
     * @param on a string recording the time
     *           of the task.
     */
    public Event(String description, String on) {
        super(description);
        this.time = on;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + time + ")";
    }


}
