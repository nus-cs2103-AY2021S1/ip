package rogue.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that can be tracked by Rogue.
 *
 * A deadline additionally has a date component to specify
 * by when the task should be completed.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param description   The task description.
     * @param by            By when the task should be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a {@code Deadline}.
     *
     * @param description   The task description.
     * @param isDone        Whether the task is completed.
     * @param by            By when the task should be completed.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public LocalDate getDate() {
        return by;
    }

    @Override
    public String summarize() {
        return String.format("D | %s | %s", super.summarize(),
                by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
