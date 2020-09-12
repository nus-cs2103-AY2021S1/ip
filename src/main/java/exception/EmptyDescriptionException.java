package exception;

/**
 * Exception that is thrown when the user sends an empty description to a command
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String s) {
        super(String.format("Description of %s is empty, please try again.", s));
    }
}
