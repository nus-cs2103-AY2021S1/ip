package viscount.exception;

/**
 * Represents the exception when the command is unknown.
 */
public class ViscountUnknownCommandException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, I do apologise but I don't know what '%s' means.";

    /**
     * Instantiates a new ViscountUnknownCommandException.
     *
     * @param command Unknown command that caused the exception.
     */
    public ViscountUnknownCommandException(String command) {
        super(String.format(ViscountUnknownCommandException.ERROR_MESSAGE, command));
    }
}
