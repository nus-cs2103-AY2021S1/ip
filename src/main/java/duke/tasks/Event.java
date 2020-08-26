package duke.tasks;

import duke.Time;

import java.time.LocalDateTime;

/**
 * Class to initiate a Event task.
 */
public class Event extends Task {
    private LocalDateTime by;

    public Event(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

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
