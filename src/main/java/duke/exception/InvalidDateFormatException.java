package duke.exception;

/**
 * The exception thrown when the user does not input any date
 * or input invalid date format when using taskbefore or taskafter
 * command.
 */
public class InvalidDateFormatException extends DukeException {

    /**
     * Constructs an InvalidDateFormatException with default message.
     * The message is "Invalid date format! Please put it something like 2020-12-31!."
     */
    public InvalidDateFormatException() {
        super("Invalid date format! Please put it something like 2020-12-31!");
    }
}
