package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task that starts at a specific time.
 */
public class Event extends Task {
    private final Date time;

    /**
     * Constructor
     *
     * @param description Description of the event.
     * @param isDone Status of the event - done or not done.
     * @param time Time of the event.
     */
    public Event(String description, boolean isDone, Date time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Returns the time of the event, in String.
     * @return Time of the event, in String
     */
    public String getTimeStr() {
        return new SimpleDateFormat("y-M-d").format(this.time);
    }

    @Override
    public String toString() {
        String box = this.isDone ? "\u2713" : "\u2718";
        return String.format("[E][%s] %s (at: %s)", box, this.description,
                new SimpleDateFormat("MMM d yyyy").format(this.time));
    }
}
