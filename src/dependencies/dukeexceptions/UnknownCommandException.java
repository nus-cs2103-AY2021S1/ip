package dependencies.dukeexceptions;

/**
 * <p>This class extends DukeExceptions, and is thrown when the user passes a command that is invalid or unknown
 * and main.java.Duke cannot parse the command.</p>
 *
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructs a new UnknownCommandException with the specified detail message.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
