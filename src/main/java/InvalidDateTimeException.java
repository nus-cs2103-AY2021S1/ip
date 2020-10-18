/**
 * Encapsulates an InvalidDateTimeException object, thrown when the user enters an invalid format
 * for the date when using a deadline or event command.
 */
public class InvalidDateTimeException extends DukeException {

    InvalidDateTimeException(String task) {
        super("OOPS!!! Please enter a valid date/time for a " + task + ".");
    }
}
