package rock.exception;

/**
 * Handle error when using invalid command.
 */
public class RockException extends Exception {
    /**
     * Constructor.
     * @param message Message to show the user.
     */
    public RockException(String message) {
        super(message);
    }
}
