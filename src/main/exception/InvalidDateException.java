package main.exception;

/**
 * Thrown to indicate the date of the deadline or event is invalid.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class InvalidDateException extends DukeException {

    /**
     * Constructs an InvalidDateException instance with a message.
     * @param message the message for the exception.
     */
    public InvalidDateException(String message) {
        super(message);
    }
}
