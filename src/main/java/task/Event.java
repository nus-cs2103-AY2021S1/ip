package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event task
 */
public class Event extends Task {
    private LocalDateTime eventAt;

    public Event(String description, LocalDateTime eventAt, boolean isDone) {
        super(description, isDone);

        assert eventAt != null : "Timing cannot be null";
        this.eventAt = eventAt;
    }

    /**
     * Returns the event time of the task.
     *
     * @return Date and time of the event.
     */
    public LocalDateTime getTiming() {
        return this.eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.eventAt.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }
}