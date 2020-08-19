/**
 * Exception that shows up when user inputs an empty description.
 */

public class EmptyDescriptionException extends Exception {

    public EmptyDescriptionException(String message) {
        super(message);
    }
}
