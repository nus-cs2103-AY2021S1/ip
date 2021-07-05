package luke.exception;

/**
 * Represents an exception thrown when loading data fails.
 */
public class LukeLoadingDataException extends LukeException {
    /**
     * Creates a LukeLoadingDataException object that indicates issue with loading data.
     *
     * @param string the situation where the error occurred
     */
    public LukeLoadingDataException(String string) {
        super(String.format("Oops. Unexpected error has occurred while %s. Please try again", string));
    }
}
