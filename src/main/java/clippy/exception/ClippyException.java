package clippy.exception;

/**
 * Represents an exception related to the operation of Clippy.
 */
public class ClippyException extends Exception {
    /**
     * Constructs an exception with a given message.
     * @param msg Message of exception.
     */
    public ClippyException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}