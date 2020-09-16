package duke.exception;

/**
 * Exception representing invalid input after event command.
 */
public class InvalidEventInputException extends DukeException {

    /**
     * Initiates exception
     */
    public InvalidEventInputException() {
        super("OOPS!!! Invalid input after event command.\n"
                + "(Format: event DESCRIPTION /at yyyy/mm/dd HHmm)");
    }
}
