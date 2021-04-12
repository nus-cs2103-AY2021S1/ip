package duke.ui;

/**
 * Represents an exception that happened in the UI logic.
 */
public class UiException extends Exception {
    public UiException (String message) {
        super(message);
    }
}
