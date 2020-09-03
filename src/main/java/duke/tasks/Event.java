package duke.tasks;

import java.time.LocalDateTime;

import duke.Time;

/**
 * Class to initiate a Event task.
 */
public class Event extends Task {
    private final LocalDateTime activityTime;

    /**
     * Constructor for Event.
     */
    public Event(String description, LocalDateTime activityTime) {
        super(description);
        this.activityTime = activityTime;
    }

    /**
     * Constructor for Event with additional argument.
     */
    public Event(String description, boolean isDone, LocalDateTime activityTime) {
        super(description, isDone);
        this.activityTime = activityTime;
    }

    public String getBy() {
        return Time.convertTimeToSave(activityTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Time.toString(activityTime) + ")";
    }
}
