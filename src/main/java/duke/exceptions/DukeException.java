package duke.exceptions;

/**
 * Exceptions caused by invalid user input.
 */
public class DukeException extends Exception {

    protected String message;
    protected String friendlyMessage;

    public DukeException(String message) {
        this.message = message;
        this.friendlyMessage = message;
    }

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
