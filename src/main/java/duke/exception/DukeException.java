package duke.exception;

/**
 * Encapsulates an exception specific to the Duke program. There are several types of exceptions, namely: wrong
 * format, unknown command, and task index out of bounds exceptions.
 */
public class DukeException extends Exception {

    /** Describes the error that occurred in more detail to the user */
    protected String additionalErrorDescription;

    /**
     * Creates and initializes a DukeException object.
     */
    public DukeException(String additionalErrorDescription) {
        this.additionalErrorDescription = additionalErrorDescription;
    }

    /**
     * Returns an error message. Informs the user what went wrong and how to avoid the error if appropriate.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return "OOPS!" + additionalErrorDescription;
    }
}
