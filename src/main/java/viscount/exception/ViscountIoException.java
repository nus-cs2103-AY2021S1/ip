package viscount.exception;

/**
 * Represents the exceptions occurring with loading or saving to data file.
 */
public class ViscountIoException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, something went wrong with %s your data.";

    public ViscountIoException(String ioAction) {
        super(String.format(ViscountIoException.ERROR_MESSAGE, ioAction));
    }
}
