package exceptions;

/**
 * Class to initiate InvalidByeCommandException.
 * Thrown when there are extra input after the bye command.
 */
public class InvalidByeCommandException extends DukeException{
    public InvalidByeCommandException() {
        super("bye command is invalid! Input \"bye\" to exit");
    }
}
