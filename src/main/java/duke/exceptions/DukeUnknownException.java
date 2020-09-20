package duke.exceptions;

/**
 * Exception Class to encapsulate any exceptional error that does not fit any of the previous criterion
 */
public class DukeUnknownException extends DukeException {
    /**
     * Constructor class for the DukeUnknownException
     * @param message message or input that caused this unexpected error.
     */
    public DukeUnknownException(String message) {
        super(message, 99);
    }
}
