import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event with a description and timing
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Creates an event task with description and timing of event, that is not done.
     * @param description Description of the event task.
     * @param at Timing event will take place.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates an event task with description and timing of event, specifying if it is done.
     * @param description Description of the event task.
     * @param at Timing event will take place.
     * @param isDone True to show task is done, False to show task is not done.
     */
    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Marks the event task as done.
     *
     * @return Event task that is marked as done.
     */
    @Override
    public Event markAsDone() {
        Event doneEvent = new Event(this.description, this.at, true);
        return doneEvent;
    }

    @Override
    public String toTxtFileFormat() {
        return "E" + super.toTxtFileFormat() + " | " + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }
}
