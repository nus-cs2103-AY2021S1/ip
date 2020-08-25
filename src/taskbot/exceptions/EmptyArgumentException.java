package taskbot.exceptions;

/**
 * This exception handles when the supplied argument is empty.
 */
public class EmptyArgumentException extends TaskbotException {
    public EmptyArgumentException(String msg) {
        super(msg);
    }
}
