package duke.task;

import duke.exception.InvalidDateInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a task that needs to be done by a particular date.
 */
public class Deadline extends Task {
    private static final String identifier = "D";
    private final LocalDate by;

    /**
     * Initialises a new instance.
     * The newly initialised deadline defaults to being incomplete.
     *
     * @param description The description of the deadline.
     * @param by          The deadline of the task in a String format "yyyy-MM-dd".
     * @throws InvalidDateInputException If the given date is not a recognised date format.
     */
    public Deadline(String description, String by) throws InvalidDateInputException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInputException(by);
        }
    }

    /**
     * Initialises a new instance.
     *
     * @param description The description of the deadline.
     * @param by          The deadline of the task in a String format "yyyy-MM-dd".
     * @param isDone      Whether the task is done.
     * @throws InvalidDateInputException If the given date is not a recognised date format.
     */
    public Deadline(String description, String by, boolean isDone)
            throws InvalidDateInputException {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInputException(by);
        }
    }

    /**
     * Returns a string representation of the deadline task.
     * This string representation is prepended by a <code>D</code> to indicate that this task is a
     * Deadline item followed by the default string representation of a normal {@link Task}.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Deadline.identifier, super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Serialises the deadline task to a string.
     * The serialised deadline task consists of a <code>D</code>, followed by a <code>|</code>, and
     * then the serialised representation of a normal {@link Task}
     *
     * @return A string representing the serialised deadline task.
     */
    @Override
    public String serialise() {
        return String.format("%s | %s | %s", Deadline.identifier, super.serialise(),
                this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
