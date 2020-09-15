package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.exception.DukeException;

/**
 * This is an Event Task.
 * Keeps a description as well as a time at which the event occurs.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate atLocalDate;

    /**
     * Constructor.
     * @param description Description of Task
     * @param at Time at which the Event occurs.
     */
    private Event(String description, String at, LocalDate atLocalDate) {
        super(description);
        this.at = at;
        this.atLocalDate = atLocalDate;
    }

    /**
     * Invoked to create a new Event Task.
     * @param description Description of Task.
     * @param at Date due for the Event.
     */
    public static Event createEvent(String description, String at) throws DukeException {
        try {
            LocalDate atLocalDate = LocalDate.parse(at);
            return new Event(description, at, atLocalDate);
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

    @Override
    public Optional<String> getFieldIdentifier() {
        return Optional.of(Task.EVENT_FIELD_IDENTIFIER);
    }

    /**
     * Returns Optional containing the event date.
     */
    @Override
    public Optional<String> getDate() {
        return Optional.of(this.at);
    }

    @Override
    public Task duplicate() {
        Event duplicateEvent = new Event(description, at, atLocalDate);
        if (this.isDone()) {
            duplicateEvent.markAsDone();
        }

        return duplicateEvent;
    }

    @Override
    public void setField(String fieldContent) throws DukeException {
        try {
            this.at = fieldContent;
            this.atLocalDate = LocalDate.parse(fieldContent);
        } catch (Exception e) {
            throw new DukeException("Date is not in YYYY-MM-DD format");
        }
    }

    @Override
    public String toString() {
        return "[" + Task.EVENT_SAVE_SYMBOL + "]" + super.toString() + " (at: "
                + atLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
