package duke.exception;

/**
 * The Unknown input exception.
 */
public class UnknownInputException extends InvalidInputException {
    /**
     * Instantiates a new Unknown input exception.
     *
     * @param message the message.
     */
    public UnknownInputException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Unknown input exception.
     */
    public UnknownInputException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
