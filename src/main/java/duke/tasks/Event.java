package duke.tasks;

import java.time.LocalDateTime;

import duke.Time;

/**
 * Class to initiate a Event task.
 */
public class Event extends Task {
    private LocalDateTime by;

    /**
     * Constructor for Event.
     */
    public Event(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Event with additional argument.
     */
    public Event(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return Time.convertTimeToSave(by);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Time.toString(by) + ")";
    }
}
