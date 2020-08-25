package viscount.exception;

/**
 * Represents the exceptions occurring with loading or saving to data file.
 */
public class ViscountIOException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, something went wrong with %s your data.";

    public ViscountIOException(String ioAction) {
        super(String.format(ViscountIOException.ERROR_MESSAGE, ioAction));
    }
}
