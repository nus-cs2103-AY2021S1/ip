package Duke.task;

/**
 * This is a subclass of Task.
 */
public class Event extends Task {
    public String time;

    /**
     * Initialize a Event object.
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
        return "[E]" + super.toString() +
                " (on: " + time + ")";
    }


}
