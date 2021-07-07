package duke.exception;

/**
 * An Exception Class regarding the incorrect format of date that the user enters.
 */
public class DukeDateException extends DukeException{
    /**
     * Constructs a new exception with the specified message.
     * @param message
     */
    public DukeDateException(String message) {
        super(message);
    }
}
