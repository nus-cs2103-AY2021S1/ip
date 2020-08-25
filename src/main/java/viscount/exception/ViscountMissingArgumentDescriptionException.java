package viscount.exception;

/**
 * Represents the exception when the description of an argument is empty.
 */
public class ViscountMissingArgumentDescriptionException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, the description of the %s argument cannot be empty.";

    public ViscountMissingArgumentDescriptionException(String argument) {
        super(String.format(ViscountMissingArgumentDescriptionException.ERROR_MESSAGE, argument));
    }
}
