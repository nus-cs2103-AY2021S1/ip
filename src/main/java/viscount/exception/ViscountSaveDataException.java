package viscount.exception;

/**
 * Represents the exception occurring when saving to data file.
 */
public class ViscountSaveDataException extends ViscountException {
    private static final String ERROR_MESSAGE = "Alas, something went wrong with saving your data.";

    /**
     * Instantiates a new ViscountSaveDataException.
     */
    public ViscountSaveDataException() {
        super(String.format(ViscountSaveDataException.ERROR_MESSAGE));
    }
}
