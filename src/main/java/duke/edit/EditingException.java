package duke.edit;

/**
 * An exception which is thrown by an Edit when an invalid edit is attempted.
 */
public class EditingException extends Exception {

    /**
     * Creates a new EditingException.
     *
     * @param message Error message to display to the user.
     */
    EditingException(String message) {
        super(message);
    }
}
