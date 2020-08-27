package alice;

/**
 * Represents the main exception thrown in Alice program.
 */
public class AliceException extends Exception {
    /**
     * Constructs an <code>AliceException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public AliceException(String message) {
        super(message);
    }
}
