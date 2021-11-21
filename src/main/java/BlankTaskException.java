/**
 * Exception that is thrown when a task has no description.
 */
public class BlankTaskException extends Exception {

    /**
     * Public constructor.
     *
     * @param message Error message.
     */
    public BlankTaskException(String message) {
        super(message);
    }
}
