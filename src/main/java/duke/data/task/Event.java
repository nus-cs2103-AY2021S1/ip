package duke.data.task;

import java.time.LocalDateTime;

//  Events: tasks that start at a specific time and ends at a specific time
//  e.g. team project meeting on 2/10/2019 2-4pm

/**
 * Represents an Event task in the task list.
 */
public class Event extends Task {

    protected LocalDateTime dateTime;
    protected String dateTimeStr;

    /**
     * Constructs an {@code Event}
     * @param description The {@code Event}'s description.
     * @param dateTime Date and time of the {@code Event}.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
        this.dateTimeStr = dateTime.format(super.formatter);
    }

    /**
     * Constructs as {@code Event}.
     * @param isDone True if {@code Event} is completed, false otherwise.
     * @param description The {@code Event}'s description.
     * @param dateTime Date and time of the {@code Event}
     */
    public Event(boolean isDone, String description, LocalDateTime dateTime) {
        super(isDone, description);
        this.dateTime = dateTime;
        this.dateTimeStr = dateTime.format(super.formatter);
    }

    @Override
    public String fileFormat() {
        return String.format("%1$s/%2$s/%3$s/%4$s", "E", super.getStatusIcon(), super.description, this.dateTimeStr);
    }

    @Override
    public String toString() {
        return String.format("[E]%1$s (at: %2$s)", super.toString(), this.dateTimeStr);
    }

}
