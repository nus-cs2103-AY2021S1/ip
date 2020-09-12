package exception;

/**
 * Exception that occurs when date is in an invalid format
 */
public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("Date is given in the wrong format! Please try again!");
    }
}
