package duke.exceptions;

/**
 * Exceptions caused by invalid user input.
 */
public class DukeException extends Exception {

    protected String message;
    protected String friendlyMessage;

    /**
     * Constructs a new DukeException with the given error message.
     *
     * @param message error message describing the DukeException
     */
    public DukeException(String message) {
        this.message = message;
        this.friendlyMessage = message;
    }

    /**
     * Constructs a new DukeException with the given error message and user friendly message.
     *
     * @param message error message describing the DukeException
     * @param friendlyMessage user friendly message to inform users of the error
     */
    public DukeException(String message, String friendlyMessage) {
        this.message = message;
        this.friendlyMessage = friendlyMessage;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns a user-friendly message describing the cause of the Exception.
     *
     * @return A user-friendly message String describing the cause of the Exception
     */
    public String getFriendlyMessage() {
        return this.friendlyMessage;
    }
}
