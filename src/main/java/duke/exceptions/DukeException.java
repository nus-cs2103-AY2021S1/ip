package duke.exceptions;

/** Represents a Duke-related exception. */
public class DukeException extends IllegalArgumentException {

    /** Constructor.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}