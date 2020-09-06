package duke.exceptions;

/** Represents a Duke-related exception. */
public class DukeException extends IllegalArgumentException {

    /** Constructs a new DukeException object with the specified error message.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
