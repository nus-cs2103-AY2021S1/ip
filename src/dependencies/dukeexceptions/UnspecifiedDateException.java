package dependencies.dukeexceptions;

/**
 * <p>This class extends DukeExceptions, and is thrown the user fails to specify a certain date or
 * deadline for a deadline or event.</p>
 *
 */
public class UnspecifiedDateException extends DukeException {
    /**
     * Constructs a new UnspecifiedDateException with the specified detail message.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public UnspecifiedDateException(String message) {
        super(message);
    }
}
