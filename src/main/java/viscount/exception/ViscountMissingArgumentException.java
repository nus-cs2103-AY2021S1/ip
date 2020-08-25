package viscount.exception;

/**
 * Represents the exception when an argument is missing.
 */
public class ViscountMissingArgumentException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, you need to specify the %s argument.";

    public ViscountMissingArgumentException(String missingArgument) {
        super(String.format(ViscountMissingArgumentException.ERROR_MESSAGE, missingArgument));
    }
}
