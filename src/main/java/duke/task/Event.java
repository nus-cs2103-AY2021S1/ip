package duke.task;

import duke.util.DateTime;

/**
 * Class representing an event.
 */
public class Event extends Task {

    /**
     * Creates a brand new {@code Event}.
     * @param description Description of the event.
     * @param at Time that the event is happening at.
     */
    public Event(String description, DateTime at) {
        super(description);
        this.dateTime = at;
        this.taskType = "E";
    }

    /**
     * Create an {@code Event} from existing data.
     * @param isDone Event completion status.
     * @param description Description of the event.
     * @param at Time that the event is happening at.
     */
    public Event(boolean isDone, String tags, String description, DateTime at) {
        this(description, at);

        if (isDone) {
            this.markDone();
        }

        this.addTagsFromData(tags);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), getDateTime());
    }
}
