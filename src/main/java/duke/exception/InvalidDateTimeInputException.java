package duke.exception;

/**
 * Exception thrown when the date and time are not in the correct format.
 */
public class InvalidDateTimeInputException extends DukeException {
    public InvalidDateTimeInputException() {
        super("Sorry please enter a valid date and time!");
    }
}
