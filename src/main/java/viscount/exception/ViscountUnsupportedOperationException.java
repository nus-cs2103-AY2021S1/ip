package viscount.exception;

/**
 * Represents the exception when the operation is unsupported.
 */
public class ViscountUnsupportedOperationException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, %s is an invalid command.";

    public ViscountUnsupportedOperationException(String command) {
        super(String.format(ViscountUnsupportedOperationException.ERROR_MESSAGE, command));
    }
}
