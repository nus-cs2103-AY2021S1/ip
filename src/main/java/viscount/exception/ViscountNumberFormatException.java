package viscount.exception;

/**
 * Represents the exception when the input number is in the wrong format.
 */
public class ViscountNumberFormatException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, '%s' is not a valid task number.";

    /**
     * Instantiates a new ViscountNumberFormatException.
     *
     * @param argument Wrongly formatted argument that caused the exception.
     */
    public ViscountNumberFormatException(String argument) {
        super(String.format(ViscountNumberFormatException.ERROR_MESSAGE, argument));
    }
}
