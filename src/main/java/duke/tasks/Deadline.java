package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Represents a deadline task. */
public class Deadline extends Task {

    /** The date which the deadline should be done by. */
    private LocalDate date;

    /** Constructs a new Deadline object with the specified description and date.
     *
     * @param name The description of the task.
     * @param date The date which the deadline should be done by.
     */
    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /** Constructs a new Deadline object with the specified indicator, description and date.
     *
     * @param isDone The indicator of whether the task is done.
     * @param name The description of the task.
     * @param date The date which the deadline should be done by.
     */
    public Deadline(boolean isDone, String name, LocalDate date) {
        super(isDone, name);
        this.date = date;
    }

    /**
     * @return The date which the deadline should be done by.
     */
    public LocalDate getDate() {
        return date;
    }

    /** Returns the string representation of the task. */
    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", "[D]", super.toString(),
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
