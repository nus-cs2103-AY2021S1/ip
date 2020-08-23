package duke.task;

import duke.exception.InvalidDateInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a task that happens on a particular date.
 */
public class Event extends Task {
    private static final String identifier = "E";
    private final LocalDate at;

    /**
     * Initialises a new instance.
     * The newly initialised event defaults to being incomplete.
     *
     * @param description The description of the event.
     * @param at          The date at which the event will happen in a String format "yyyy-MM-dd".
     * @throws InvalidDateInputException If the given date is not a recognised date format.
     */
    public Event(String description, String at) throws InvalidDateInputException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInputException(at);
        }
    }

    /**
     * Initialises a new instance.
     *
     * @param description The description of the event.
     * @param at          The date at which the event will happen in a String format "yyyy-MM-dd".
     * @param isDone      Whether the event is done.
     * @throws InvalidDateInputException If the given date is not a recognised date format.
     */
    public Event(String description, String at, boolean isDone) throws InvalidDateInputException {
        super(description, isDone);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInputException(at);
        }
    }

    /**
     * Returns a string representation of the event.
     * This string representation is prepended by a <code>E</code> to indicate that this task is a
     * Event item followed by the default string representation of a normal {@link Task}.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", Event.identifier, super.toString(),
                this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Serialises the event to a string.
     * The serialised event consists of a <code>E</code>, followed by a <code>|</code>, and
     * then the serialised representation of a normal {@link Task}
     *
     * @return A string representing the serialised event.
     */
    @Override
    public String serialise() {
        return String.format("%s | %s | %s", Event.identifier, super.serialise(),
                this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
