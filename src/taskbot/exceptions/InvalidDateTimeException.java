package taskbot.exceptions;

/**
 * This exception handles when user inputs
 * an invalid time or date.
 */
public class InvalidDateTimeException extends TaskbotException {
    public InvalidDateTimeException(String message) {
        super(message);
    }
}
