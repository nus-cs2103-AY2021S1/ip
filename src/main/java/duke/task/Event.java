package duke.task;

import java.util.Date;

import duke.util.DateFormatter;

/**
 * Models an event task.
 */
public class Event extends Task {
    protected Date at;

    /**
     * Constructs an Event.
     *
     * @param description The description of the event.
     * @param at The date of the event.
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns icon representing whether the task is done or not.
     *
     * @return Icon representing whether the task is done or not.
     */
    @Override
    public String toSaveFormat() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0,
                this.description, DateFormatter.formatSave(this.at));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                DateFormatter.formatDisplay(this.at));
    }
}
