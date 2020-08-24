package exceptions;

/**
 * Thrown if a user enters an invalid date-time format when adding a task
 */

public class BadDtFormatException extends DukeException {
    public BadDtFormatException(String message) {
        super(message);
    }
}
