package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task with the occurring time specified.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Event constructor.
     *
     * @param description the task description.
     * @param at          the occurring time.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Event constructor.
     *
     * @param description the task description.
     * @param at          the occurring time.
     * @param isDone      specify whether the task is done or not.
     */
    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    private String dateFormat() {
        return this.at.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateFormat() + ")";
    }

    @Override
    public String writeToFile() {
        return String.format("E | %s | %s", super.writeToFile(), this.at);
    }
}
