package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks that need to be done before a specific date and time.
 */
public class Deadline extends Task {
    /** The date by which this task must be done. */
    protected LocalDate byDate;
    /** The time by which this task must be done. */
    protected LocalTime byTime;

    /**
     * Creates a new deadline with the specified description, specified date and specified time.
     *
     * @param description The description of the deadline.
     * @param byDate The date by which the task must be done.
     * @param byTime The time by which the task must be done.
     */
    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description, TaskType.DEADLINE);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Returns true since deadlines have dates associated to them.
     *
     * @return True, since deadlines have dates associated to them.
     */
    @Override
    public boolean hasDate() {
        return true;
    }

    /**
     * If this deadline has a time associated to it, returns true, otherwise false.
     *
     * @return True, if this deadline has a time associated to it, otherwise false.
     */
    @Override
    public boolean hasTime() {
        return byTime != null;
    }

    /**
     * Gets the date by which this task must be done.
     *
     * @return The date by which this task must be done.
     */
    @Override
    public LocalDate getDate() {
        return this.byDate;
    }

    /**
     * Gets the time by which this task must be done.
     *
     * @return The time by which this task must be done.
     */
    @Override
    public LocalTime getTime() {
        return this.byTime;
    }

    /**
     * Returns a string representation of this deadline.
     *
     * @return String representation of this deadline.
     */
    @Override
    public String toString() {
        if (this.hasTime()) {
            return String.format("%s (by: %s %s)",
                    super.toString(),
                    byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                    byTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        } else {
            return String.format("%s (by: %s)",
                    super.toString(),
                    byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
    }
}
