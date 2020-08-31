package viscount.exception;

/**
 * Represents the exceptions occurring with loading or saving to data file.
 */
public class ViscountSaveDataException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, something went wrong with saving your data.";

    public ViscountSaveDataException() {
        super(String.format(ViscountSaveDataException.ERROR_MESSAGE));
    }
}
