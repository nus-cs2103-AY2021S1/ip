package duke.exception;

/**
 * Exception representing invalid input after deadline command.
 */
public class InvalidDeadlineInputException extends DukeException {

    /**
     * Initiates exception
     */
    public InvalidDeadlineInputException() {
        super("OOPS!!! Invalid input after deadline command.\n"
                + "(Format: deadline DESCRIPTION /by yyyy/mm/dd HHmm)");
    }
}
