package exception;

/**
 * Encapsulates the message of an exception or error related to Duke's operation.
 *  It is to be thrown when extracting date and time from user's command input of
 *  incorrect format.
 *
 * <p>The 'DateTimeInvalidFormatException' supports operators, supported include: </p>
 *
 * <p> (i) Getters to error message </p>
 */
public class DateTimeInvalidFormatException extends InvalidActionException {

    /**
     * Constructor of this object.
     */
    public DateTimeInvalidFormatException() {
        super("Action invalid. Date and Time Format incorrect.");
    }

    /**
     * Constructor of this object.
     * @param message error message of this exception.
     */
    public DateTimeInvalidFormatException(String message) {
        super(message);
    }
}
