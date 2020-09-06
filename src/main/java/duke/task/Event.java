package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * This is an Event Task.
 * Keeps a description as well as a time at which the event occurs.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate atLocalDate;

    /**
     * Constructs a new Event Task.
     * @param description Description of Task
     * @param at Time at which the Event occurs.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        try {
            this.atLocalDate = LocalDate.parse(at);
        } catch (Exception e) {
            throw new DukeException("Date is not in YYYY-MM-DD format");
        }
    }

    /**
     * Returns the letter E for writing to hard disk file.
     */
    @Override
    public String getSaveSymbol() {
        return Task.EVENT_SAVE_SYMBOL;
    }

    /**
     * Returns Optional containing the event date.
     */
    @Override
    public Optional<String> getDate() {
        return Optional.of(this.at);
    }

    @Override
    public String toString() {
        return "[" + Task.EVENT_SAVE_SYMBOL + "]" + super.toString() + " (at: "
                + atLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
