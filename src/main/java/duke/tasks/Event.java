package duke.tasks;

import java.time.LocalDateTime;

import duke.Time;

/**
 * Class to initiate a Event task.
 */
public class Event extends Task {
    private final LocalDateTime activityTime;

    /**
     * Initializes Event.
     */
    public Event(String description, LocalDateTime activityTime) {
        super(description);
        this.activityTime = activityTime;
    }

    /**
     * Initializes Event with additional argument compared to the first constructor.
     */
    public Event(String description, boolean isDone, LocalDateTime activityTime) {
        super(description, isDone);
        this.activityTime = activityTime;
    }

    public String getActivityTimeInString() {
        return Time.convertTimeToSave(activityTime);
    }

    public LocalDateTime getActivityTime() {
        return activityTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Time.toString(activityTime) + ")";
    }
}
