package exceptions;

/**
 * Thrown if the user enters an unknown command.
 */
public class UnknownCmdException extends DukeException {
    public UnknownCmdException(String message) {
        super(message);
    }
}
