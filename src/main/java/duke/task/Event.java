package duke.task;

import duke.format.DateFormat;

import java.util.Date;

/**
 * Represents an event type of task with the date of event.
 */
public class Event extends Task {
    protected Date at;

    /**
     * Creates an instance of event.
     * @param name the name of event
     * @param isComplete the completion status of event
     * @param at the date the event is held on
     */
    public Event(String name, boolean isComplete, Date at) {
        super(name, isComplete, TaskType.EVENT);
        this.at = at;
    }

    /**
     * Gets the date of the event.
     * @return the date of the event
     */
    @Override
    public Date getDate() {
        return this.at;
    }

    /**
     * Represents the event in string.
     * @return the string representation of the event
     */
    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), DateFormat.formatDate(this.at));
    }
}
