package exception;

/**
 * Triggers when a user input has a different time format
 * as what was specified in Duke.
 */
public class InvalidDateTimeFormatException extends DukeException {

    /**
     * Initialises the exception that warns users that
     * their input format for the date and time
     * is wrong.
     *
     * @param message The warning message to the user.
     */
    public InvalidDateTimeFormatException(String message) {
        super(message);
    }
}
