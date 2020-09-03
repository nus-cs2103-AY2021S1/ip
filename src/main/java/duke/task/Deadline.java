package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        this(description, by, false);
    }

    /**
     * The deadline constructor.
     *
     * @param description The description of the deadline.
     * @param by The date/time the deadline is due by.
     * @param isDone The boolean keeping track of whether the deadline is done.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns a boolean indicating if the deadline is due by the given date.
     *
     * @param date A date.
     * @return true if the deadline is due by the given date.
     */
    @Override
    public boolean isDue(LocalDate date) {
        return this.by.toLocalDate().equals(date);
    }

    @Override
    public String toSaveData() {
        return "D | " + super.toSaveData() + " | " + this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }
}
