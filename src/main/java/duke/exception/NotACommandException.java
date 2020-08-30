package duke.exception;

/**
 * The exception thrown when the user input something
 * that duke does not recognized as a command.
 */
public class NotACommandException extends DukeException {

    /**
     * Constructs a NotACommandException with default message.
     * The message is "OOPS!!! I'm sorry, but I don't know what that means :-("
     */
    public NotACommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
