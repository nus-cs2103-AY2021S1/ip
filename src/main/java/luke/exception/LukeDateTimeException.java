package luke.exception;

/**
 * Represents an exception thrown when incorrect DateTime is given.
 */
public class LukeDateTimeException extends LukeException {
    /**
     * Creates a LukeDateTimeException object that indicates incorrect DateTime format.
     *
     * @param command the DateTime format given by the user
     */
    public LukeDateTimeException(String command) {
        super(String.format("Unable to read date and time for '%s' command. Please enter the date and time in the format 'YYYY-MM-DD'", command));
    }
}
