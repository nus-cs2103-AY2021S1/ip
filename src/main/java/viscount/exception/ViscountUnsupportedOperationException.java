package viscount.exception;

/**
 * Represents the exception when the operation is unsupported.
 */
public class ViscountUnsupportedOperationException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, this command does not support %s.";

    public ViscountUnsupportedOperationException(String command) {
        super(String.format(ViscountUnsupportedOperationException.ERROR_MESSAGE, command));
    }
}
