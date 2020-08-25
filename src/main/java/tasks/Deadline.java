package tasks;

import utils.DateTimeStringFormatter;
import utils.ResourceHandler;
import utils.Schedulable;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * A specialised {@code Task} that needs to be done before a specific date/time.
 */
public class Deadline extends Task implements Schedulable {
    private final LocalDateTime dueDate;

    /**
     * Constructs a new uncompleted {@code Deadline} object.
     *
     * @param name the name of the {@code Deadline}.
     * @param dueDate a {@code LocalDueDate} object representing the due date of the {@code Deadline}.
     */
    public Deadline(String name, LocalDateTime dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a new {@code Deadline} object.
     *
     * @param name the name of the {@code Deadline}.
     * @param dueDate a {@code LocalDueDate} object representing the due date of the {@code Deadline}.
     * @param isDone whether the {@code Deadline} has been completed.
     */
    private Deadline(String name, LocalDateTime dueDate, boolean isDone) {
        super(name, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Marks the {@code Deadline} as done.
     *
     * @return a new completed {@code Deadline} object with the same {@code name} as this {@code Deadline}.
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(name, dueDate, true);
    }

    /**
     * Returns the due date of this {@code Deadline} object.
     *
     * @return the due date of this {@code Deadline} object.
     */
    @Override
    public LocalDateTime getDateTime() {
        return dueDate;
    }

    /**
     * Returns whether the due date of this {@code Deadline} object has elapsed.
     *
     * @return whether the due date of this {@code Deadline} object has elapsed.
     */
    @Override
    public boolean hasDateTimeElapsed() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return dueDate.isBefore(currentDateTime);
    }

    /**
     * Returns a string representation of this {@code Deadline} object.
     *
     * @return a string representation of the {@code Deadline}.
     */
    @Override
    public String toString() {
        return MessageFormat.format(ResourceHandler.getString("deadline.toString"), super.toString(),
                DateTimeStringFormatter.formatDateTime(dueDate));
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
        return dueDate.compareTo(schedulable.getDateTime());
    }
}
