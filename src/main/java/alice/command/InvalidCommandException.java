package alice.command;

import alice.AliceException;

/**
 * Throws when the command given by the user is invalid.
 */
public class InvalidCommandException extends AliceException {
    /**
     * Constructs a {@code InvalidCommandException} with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
