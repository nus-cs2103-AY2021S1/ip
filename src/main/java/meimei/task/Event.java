package meimei.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event at a particular date and time. Inherits from Task.
 */
public class Event extends Task {
    /** Represents the date and time of the event */
    protected LocalDateTime dateTime;

    /**
     * Public constructor.
     *
     * @param taskName Name of the task as given by user.
     * @param dateTime LocalDateTime object representing the date and time of the event.
     */
    public Event(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    /**
     * Returns the date and time when the event is held.
     *
     * @return LocalDateTime object representing the date and time of the event.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, h.m a"));
    }
}
