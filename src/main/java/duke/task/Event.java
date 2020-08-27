package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class for task of event type.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Public constructor for a event task.
     *
     * Requires an description and a time of the
     * event of LocalDateTime form.
     * @param description Describes the event task
     * @param at Date/time of the event task
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public LocalDateTime getAt() { return at; }

    public void setAt(LocalDateTime at) { this.at = at; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma")) + ")";
    }
}