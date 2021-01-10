package duke;

/**
 * Represents an exception specific to the Duke chat-bot that is thrown with a corresponding message.
 */
public class DukeException extends Exception {

    /**
     * Initialises the exception with a message to be associated with the exception.
     *
     * @param errorMessage Message to be associated with the exception.
     * This is also usually printed out to the user when the exception is thrown.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
