package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks that occur at a specific date and time.
 */
public class Event extends Task {
    /** The date at which this task occurs. */
    protected LocalDate atDate;
    /** The time at which this task occurs. */
    protected LocalTime atTime;

    /**
     * Creates a new event with the specified description, specified date and specified time.
     *
     * @param description The description of the event.
     * @param atDate The date at which the event occurs.
     * @param atTime The time at which the event occurs.
     */
    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description, TaskType.EVENT);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    /**
     * Returns true since events have dates associated to them.
     *
     * @return True, since events have dates associated to them.
     */
    @Override
    public boolean hasDate() {
        return true;
    }

    /**
     * If this event has a time associated to it, returns true, otherwise false.
     *
     * @return True, if this event has a time associated to it, otherwise false.
     */
    @Override
    public boolean hasTime() {
        return atTime != null;
    }

    /**
     * Gets the date at which this task occurs.
     *
     * @return The date at which this task occurs.
     */
    @Override
    public LocalDate getDate() {
        return this.atDate;
    }

    /**
     * Gets the time at which this task occurs.
     *
     * @return The time at which this task occurs.
     */
    @Override
    public LocalTime getTime() {
        return this.atTime;
    }

    /**
     * Returns a string representation of this event.
     *
     * @return String representation of this event.
     */
    @Override
    public String toString() {
        if (this.hasTime()) {
            return String.format("%s (at: %s %s)",
                    super.toString(),
                    atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                    atTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        } else {
            return String.format("%s (at: %s)",
                    super.toString(),
                    atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
    }
}
