package duke.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Event type of Task.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event-type Tasks.
     *
     * @param description Description of event input by user.
     * @param at Date on which event occurs.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for Event-type tasks loaded from memory.
     *
     * @param description Description of event input by user.
     * @param isDone Current state of event.
     * @param at Date on which event occurs.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        String formattedDate = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

    /**
     * Generates String with relevant dat to save to disk.
     *
     * @return String that contains data to save.
     */
    public String toDataString() {
        return String.format("E|%s|%s|%s", super.isDone, super.description, this.at);
    }

    public LocalDate getAt() {
        return at;
    }
}
