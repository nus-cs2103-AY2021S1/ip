package tickbot.exception;

/**
 * The exception to represent that the data storage is corrupted.
 */
public class CorruptedDataException extends Exception {
    private static final long serialVersionUID = 1L;

    public CorruptedDataException() {
        super("CorruptedDataException");
    }
}
