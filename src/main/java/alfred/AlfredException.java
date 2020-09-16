package alfred;

/**
 * Custom exception class for Alfred.
 */
public class AlfredException extends Exception {

    /**
     * Throws a new AlfredException.
     *
     * @param message Message to be displayed in exception.
     */
    public AlfredException(String message) {
        super("Action failed: " + message);
    }
}
