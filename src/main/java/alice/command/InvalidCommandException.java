package alice.command;

import alice.AliceException;

/**
 * Thrown when the command given by the user is invalid.
 */
public class InvalidCommandException extends AliceException {
    /**
     * Constructs a <code>InvalidCommandException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
