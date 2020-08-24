package duke.exceptions;

/** Wrapper exception for all exceptions related to Duke. */
public class DukeException extends IllegalArgumentException {

    /** Constructs a DukeException with the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeException(String message) {
        super(message);
    }

}
