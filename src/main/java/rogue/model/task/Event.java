package rogue.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that can be tracked by Rogue.
 *
 * An event additionally has a date component to specify
 * when the task will happen.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Constructs an {@code Event}.
     *
     * @param description   The task description.
     * @param at            By when the task should be completed.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs an {@code Event}.
     *
     * @param description   The task description.
     * @param isDone        Whether the task is completed.
     * @param at            By when the task should be completed.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public LocalDate getDate() {
        return at;
    }

    @Override
    public String summarize() {
        return String.format("E | %s | %s", super.summarize(),
                at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

