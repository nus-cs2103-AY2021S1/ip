package duke.task;

/**
 * Represents a Event task.
 */
public class Event extends Task {
    private String time;

    /**
     * Constructs a Event object.
     *
     * @param description A string describing
     *                    the task.
     * @param on A string recording the time
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
