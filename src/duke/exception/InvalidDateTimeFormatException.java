package duke.exception;

/**
 * The exception thrown when the user does not input any date
 * or input invalid date format when using deadline or event
 * command.
 */
public class InvalidDateTimeFormatException extends DukeException {

    /**
     * Constructs an InvalidDateTimeFormatException with default message.
     * The message is "Invalid date format!
     * Please put it something like 2020-12-31 1800 for 31 December 2020 6 pm."
     */
    public InvalidDateTimeFormatException() {
        super("Invalid date format! Please put it something" +
                "like 2020-12-31 1800 for 31 December 2020 6 pm.");
    }
}
