package duke.tasks;

import duke.utils.DateTimeStringFormatter;
import duke.utils.ResourceHandler;
import duke.utils.Schedulable;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * A specialised {@code Task} that starts and ends at a specific time.
 */
public class Event extends Task implements Schedulable {
    private final LocalDateTime dateTime;

    /**
     * Constructs a new uncompleted {@code Event} object.
     *
     * @param name the name of the {@code Event}.
     * @param dateTime a {@code LocalDueDate} object representing the date and time of the {@code Event}.
     */
    public Event(String name, LocalDateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    /**
     * Constructs a new uncompleted {@code Event} object.
     *
     * @param name the name of the {@code Event}.
     * @param dateTime a {@code LocalDueDate} object representing the date and time of the {@code Event}.
     * @param isDone whether the {@code Event} has been completed.
     */
    public Event(String name, LocalDateTime dateTime, boolean isDone) {
        super(name, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Marks the {@code Event} as done.
     *
     * @return a new completed {@code Event} object with the same {@code name} as this {@code Event}.
     */
    @Override
    public Event markAsDone() {
        return new Event(name, dateTime, true);
    }

    /**
     * Returns the datetime of this {@code Event} object.
     *
     * @return the datetime of this {@code Event} object.
     */
    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns whether the datetime of this {@code Event} object has elapsed.
     *
     * @return whether the datetime of this {@code Event} object has elapsed.
     */
    @Override
    public boolean hasDateTimeElapsed() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return dateTime.isBefore(currentDateTime);
    }

    /**
     * Returns a string representation of this {@code Event} object.
     *
     * @return a string representation of the {@code Event}.
     */
    @Override
    public String toString() {
        return MessageFormat.format(ResourceHandler.getString("event.toString"), super.toString(),
                DateTimeStringFormatter.formatDateTime(dateTime));
    }

    /**
     * Compares this {@code Schedulable} object with another {@code Schedulable Object}
     *
     * @param schedulable the {@code Schedulable} object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Schedulable schedulable) {
        return dateTime.compareTo(schedulable.getDateTime());
    }
}
