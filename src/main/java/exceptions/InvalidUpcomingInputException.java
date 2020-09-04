package exceptions;

/**
 * Thrown if an invalid number of days is entered for the "upcoming" command.
 */
public class InvalidUpcomingInputException extends DukeException {
    public InvalidUpcomingInputException(String message) {
        super(message);
    }
}
