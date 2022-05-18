package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a Task that has a set date and time when it will be completed/occur.
 */
public class Event extends Task {

    private LocalDateTime date;

    /**
     * Constructs an Event with the given description and date.
     *
     * @param description description of the Event
     * @param date datetime of the Event
     */
    public Event(String description, LocalDateTime date) {
        super(description);
        super.symbol = 'E';
        this.date = date;
    }

    /**
     * Constructs an Event with the given description, date and completion status.
     *
     * @param description description of the Event
     * @param date datetime of the Event
     * @param isCompleted whether the Event is completed
     */
    public Event(String description, LocalDateTime date, boolean isCompleted) {
        super(description, isCompleted);
        super.symbol = 'E';
        this.date = date;
    }

    @Override
    public Event markCompleted() {
        return new Event(description, date, true);
    }

    @Override
    public String getStorageString() {
        String baseString = super.getStorageString();
        return String.format("%s | %s", baseString, date);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", symbol, super.toString(), date);
    }
}
